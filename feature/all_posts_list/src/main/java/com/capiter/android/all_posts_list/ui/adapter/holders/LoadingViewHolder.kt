package com.capiter.android.all_posts_list.ui.adapter.holders

import android.view.LayoutInflater
import com.capiter.android.all_posts_list.databinding.ListItemLoadingBinding
import com.capiter.android.all_posts_list.databinding.ListItemPostBinding
import com.capiter.android.core.network.responses.Post
import com.capiter.android.ui.base.BaseViewHolder

class LoadingViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemLoadingBinding>(
    binding = ListItemLoadingBinding.inflate(inflater)
)
