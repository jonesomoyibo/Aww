package com.capiter.android.all_posts_list.ui.adapter.holders

import android.view.LayoutInflater
import com.capiter.android.all_posts_list.databinding.ListItemPostBinding
import com.capiter.android.all_posts_list.ui.AllPostListViewModel
import com.capiter.android.all_posts_list.ui.model.PostItem
import com.capiter.android.ui.base.BaseViewHolder

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
    fun bind(viewModel: AllPostListViewModel, item: PostItem) {
        binding.postItem = item
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }
}