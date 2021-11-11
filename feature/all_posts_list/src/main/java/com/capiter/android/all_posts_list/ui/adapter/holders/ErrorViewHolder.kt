package com.capiter.android.all_posts_list.ui.adapter.holders

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.capiter.android.all_posts_list.databinding.ListItemErrorBinding
import com.capiter.android.all_posts_list.ui.PostListViewModel
import com.capiter.android.ui.base.BaseViewHolder

/**
 * Class describes posts  error view and metadata about its place within the [RecyclerView].
 *
 * @see BaseViewHolder
 */
class ErrorViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemErrorBinding>(
    binding = ListItemErrorBinding.inflate(inflater)
) {

    /**
     * Bind view data binding variables.
     *
     * @param viewModel POST list view model.
     */
    fun bind(viewModel: PostListViewModel) {
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }
}
