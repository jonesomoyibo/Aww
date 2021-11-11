package com.capiter.android.all_posts_list.ui

import com.capiter.android.ui.base.BaseViewState

sealed class FavouriteIconViewState:BaseViewState {



    object AddToFavorite :FavouriteIconViewState()

    object AddedToFavorite :FavouriteIconViewState()

    object AlreadyAddedToFavorite :FavouriteIconViewState()

    fun isAddToFavorite() = this is AddToFavorite

    /**
     * Check if current view state is [AddedToFavorite].
     *
     * @return True if is added to favorite state, otherwise false.
     */
    fun isAddedToFavorite() = this is AddedToFavorite

    /**
     * Check if current view state is [AlreadyAddedToFavorite].
     *
     * @return True if is already added to favorite state, otherwise false.
     */
    fun isAlreadyAddedToFavorite() = this is AlreadyAddedToFavorite
}