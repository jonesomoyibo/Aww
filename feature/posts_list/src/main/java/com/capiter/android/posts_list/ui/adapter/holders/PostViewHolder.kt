package com.capiter.android.posts_list.ui.adapter.holders

import android.view.LayoutInflater
import com.capiter.android.posts_list.ui.PostListViewModel
import com.capiter.android.posts_list.ui.model.PostItem
import com.capiter.android.ui.base.BaseViewHolder
import posts_list.databinding.ListItemPostBinding

class PostViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemPostBinding>(
    binding = ListItemPostBinding.inflate(inflater)
) {
    /**
     * Bind view data binding variables.
     *
     * @param PostFavorite Post favorite to bind.
     */
    fun bind(viewModel: PostListViewModel, item: PostItem) {
        binding.postItem = item
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }
}