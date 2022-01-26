package com.capiter.android.posts_list.ui

import com.capiter.android.posts_list.ui.model.PostItem

sealed class AddPostToFavouriteEvent{
/**
 * Open character detail view.
 *
 * @param id post to add to favourites
 */
data class AddPostToFavourites(val post: PostItem) : AddPostToFavouriteEvent()

}