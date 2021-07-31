package com.nie.githublist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.nie.githublist.base.BaseViewModel
import com.nie.githublist.data.remote.model.PersonalInfo
import com.nie.githublist.data.remote.model.User
import com.nie.githublist.repository.MainRepository
import com.nie.githublist.repository.UserListRemoteRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MainViewModel(
    private val userListRemoteRepository: UserListRemoteRepository,
    private val mainRepository: MainRepository
) : BaseViewModel() {

    private val _users = MutableLiveData<PagingData<User>>()
    val users: LiveData<PagingData<User>> = _users

    private val _personalInfo = MutableLiveData<PersonalInfo>()
    val personalInfo: LiveData<PersonalInfo> = _personalInfo

    @OptIn(ExperimentalCoroutinesApi::class)
    fun fetchUsers() {
        userListRemoteRepository.fetchUserList()
            .cachedIn(viewModelScope)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _users.value = it
            }, {

            }).addTo(compositeDisposable)
    }

    fun fetchPersonalInfo() {
        mainRepository.fetchPersonalInfo()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _personalInfo.value = it
            }, {

            }).addTo(compositeDisposable)
    }
}