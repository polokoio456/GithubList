package com.nie.githublist.ui.main.fragment

import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nie.githublist.R
import com.nie.githublist.base.BaseFragment
import com.nie.githublist.data.remote.model.PersonalInfo
import com.nie.githublist.databinding.FragmentPersonalInfoBinding
import com.nie.githublist.extension.getColorFromAttr
import com.nie.githublist.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PersonalInfoFragment : BaseFragment() {

    private lateinit var binding: FragmentPersonalInfoBinding

    override val viewModel by sharedViewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPress()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPersonalInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchPersonalInfo()

        observableLiveData()
    }

    private fun observableLiveData() {
        viewModel.personalInfo.observe(viewLifecycleOwner, {
            Glide.with(requireActivity())
                .load(it.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.imageAvatar)

            binding.textName.text = it.name
            binding.textLogin.text = it.login
            binding.textEmail.text = it.email
            setTextFollowers(it)
        })
    }

    private fun setTextFollowers(info: PersonalInfo) {
        val spannableString = SpannableString(requireActivity().getString(R.string.follows).format(info.followers, info.following))
        val strFollowers = "${info.followers}"
        val startIndexFollows = spannableString.indexOf(strFollowers)
        val endIndexFollowers = startIndexFollows + strFollowers.length

        val strFollowing = "${info.following}"
        val startIndexFollowing = spannableString.indexOf(strFollowing)
        val endIndexFollowing = startIndexFollowing + strFollowing.length

        spannableString.apply {
            setSpan(
                ForegroundColorSpan(
                    requireContext().getColorFromAttr(R.attr.colorText)
                ),
                startIndexFollows,
                endIndexFollowers,
                0
            )

            setSpan(
                ForegroundColorSpan(
                    requireContext().getColorFromAttr(R.attr.colorText)
                ),
                startIndexFollowing,
                endIndexFollowing,
                0
            )
        }

        binding.textFollows.setText(spannableString, TextView.BufferType.SPANNABLE)
    }

    private fun handleBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        })
    }
}