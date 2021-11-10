package com.capiter.android.favourite_posts_list.adapter.holders

import android.view.LayoutInflater
import com.capiter.android.core.database.entities.Post
import com.capiter.android.favourite_posts_list.databinding.ListItemPostFavouriteBinding
import com.capiter.android.ui.base.BaseViewHolder

class FavouritePostsViewHolder (
    inflater: LayoutInflater
) : BaseViewHolder<ListItemPostFavouriteBinding>(
    binding = ListItemPostFavouriteBinding.inflate(inflater)
) {
    /**
     * Bind view data binding variables.
     *
     * @param FavoritePost post favorite to bind.
     */
    fun bind(postItem: Post) {
        binding.postItem = postItem
        binding.executePendingBindings()
    }
}
