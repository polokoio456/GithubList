package com.nie.githublist.ui.userdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nie.githublist.base.BaseViewModel
import com.nie.githublist.data.remote.model.UserDetail
import com.nie.githublist.repository.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo

class UserDetailViewModel(private val mainRepository: MainRepository) : BaseViewModel() {

    private val _userDetail = MutableLiveData<UserDetail>()
    val userDetail: LiveData<UserDetail> = _userDetail

    fun fetchUserDetail(name: String) {
        mainRepository.fetchUserDetail(name)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading() }
            .doFinally { hideLoading() }
            .subscribe({
                _userDetail.value = it
            },{

            }).addTo(compositeDisposable)
    }
}