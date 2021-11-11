package com.capiter.android.all_posts_list.ui

import com.capiter.android.all_posts_list.ui.model.PostItem
import com.capiter.android.core.database.entities.Post

sealed class AddPostToFavouriteEvent{
/**
 * Open character detail view.
 *
 * @param id post to add to favourites
 */
data class AddPostToFavourites(val post: PostItem) : AddPostToFavouriteEvent()

}