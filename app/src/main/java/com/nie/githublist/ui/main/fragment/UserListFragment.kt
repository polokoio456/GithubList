package com.nie.githublist.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.nie.githublist.base.BaseFragment
import com.nie.githublist.databinding.FragmentUserListBinding
import com.nie.githublist.ui.main.MainViewModel
import com.nie.githublist.ui.main.UserListAdapter
import com.nie.githublist.ui.userdetail.UserDetailActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class UserListFragment : BaseFragment() {

    private lateinit var binding: FragmentUserListBinding

    override val viewModel by sharedViewModel<MainViewModel>()

    private val adapter by inject<UserListAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPress()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.userRecyclerView.adapter = adapter

        viewModel.fetchUsers()

        setOnClickListener()
        observableLiveData()
    }

    private fun setOnClickListener() {
        adapter.onItemClicked = {
            UserDetailActivity.start(requireActivity(), it.login)
        }
    }

    private fun observableLiveData() {
        viewModel.users.observe(viewLifecycleOwner, {
            adapter.submitData(lifecycle, it)
        })
    }

    private fun handleBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        })
    }
}