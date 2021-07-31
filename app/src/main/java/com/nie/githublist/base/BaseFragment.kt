package com.nie.githublist.base

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import com.nie.githublist.R

abstract class BaseFragment : Fragment() {

    protected abstract val viewModel: BaseViewModel?

    private lateinit var loadingDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadingDialog = AlertDialog.Builder(activity)
            .setCancelable(false)
            .setView(LayoutInflater.from(activity).inflate(R.layout.dialog_progress, null))
            .create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel?.showLoading?.observe(viewLifecycleOwner, {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        })

        super.onViewCreated(view, savedInstanceState)
    }

    private fun showLoading() {
        if (isAdded) {
            loadingDialog.show()
        }
    }

    private fun hideLoading() {
        if (isAdded) {
            loadingDialog.dismiss()
        }
    }
}