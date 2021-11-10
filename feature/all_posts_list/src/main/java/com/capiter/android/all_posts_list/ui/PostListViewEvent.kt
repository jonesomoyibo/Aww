package com.capiter.android.all_posts_list.ui

import com.capiter.android.core.database.entities.Post

sealed class PostListViewEvent{
/**
 * Open character detail view.
 *
 * @param id post identifier
 */
data class OpenPostDetail(val id: Long) : PostListViewEvent()

}