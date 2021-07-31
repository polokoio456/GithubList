package com.nie.githublist.data.paging

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.nie.githublist.data.remote.model.User
import com.nie.githublist.repository.MainRepository
import io.reactivex.Single

class UserListRxPagingSource(private val mainRepository: MainRepository) : RxPagingSource<Int, User>() {
    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, User>> {
        return mainRepository.fetchUserList(params.key ?: 0)
            .map {
                LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = it.last().id
                )
            }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return null
    }
}