package com.nie.githublist.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jakewharton.rxbinding2.view.RxView
import com.nie.githublist.data.remote.model.User
import com.nie.githublist.databinding.ItemUserBinding
import com.nie.githublist.extension.throttleClick
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class UserListAdapter : PagingDataAdapter<User, UserListAdapter.UserListViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }

    private val compositeDisposable = CompositeDisposable()

    var onItemClicked = { _: User ->  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, onItemClicked, compositeDisposable)
        }
    }

    fun clear() = compositeDisposable.clear()

    class UserListViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: User, onItemClicked: (User) -> Unit, compositeDisposable: CompositeDisposable) {
            Glide.with(binding.root.context)
                .load(item.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.imageAvatar)

            binding.textTitle.text = item.login

            RxView.clicks(itemView)
                .throttleClick()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    onItemClicked.invoke(item)
                }.addTo(compositeDisposable)
        }
    }
}