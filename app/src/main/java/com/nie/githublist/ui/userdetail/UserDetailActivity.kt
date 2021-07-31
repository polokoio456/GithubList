package com.nie.githublist.ui.userdetail

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jakewharton.rxbinding2.view.RxView
import com.nie.githublist.base.BaseActivity
import com.nie.githublist.databinding.ActivityUserDetailBinding
import com.nie.githublist.extension.throttleClick
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import org.koin.android.ext.android.inject

class UserDetailActivity : BaseActivity() {

    companion object {
        private const val USER_NAME = "user_name"

        fun start(activity: Activity, name: String) {
            Intent(activity, UserDetailActivity::class.java).apply {
                putExtra(USER_NAME, name)
            }.let { activity.startActivity(it) }
        }
    }

    private val binding by lazy { ActivityUserDetailBinding.inflate(layoutInflater) }

    override val viewModel by inject<UserDetailViewModel>()

    private val userName by lazy { intent.getStringExtra(USER_NAME)!! }

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.fetchUserDetail(userName)

        setOnClickListener()
        observableLiveData()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    private fun setOnClickListener() {
        RxView.clicks(binding.imageExit)
            .throttleClick()
            .subscribe {
                finish()
            }.addTo(compositeDisposable)
    }

    private fun observableLiveData() {
        viewModel.userDetail.observe(this, { detail ->
            Glide.with(this)
                .load(detail.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.imageAvatar)

            binding.viewPersonalInfoName.text = detail.name

            if (detail.location.isNullOrEmpty()) {
                binding.viewPersonalInfoLocation.visibility = View.GONE
            } else {
                binding.viewPersonalInfoLocation.text = detail.location
            }

            binding.viewPersonalInfoGithubUrl.setTextBaseLine(detail.githubUrl)

            RxView.clicks(binding.viewPersonalInfoGithubUrl)
                .throttleClick()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse(detail.githubUrl)
                    }.let { intent -> startActivity(intent) }
                }.addTo(compositeDisposable)
        })
    }
}