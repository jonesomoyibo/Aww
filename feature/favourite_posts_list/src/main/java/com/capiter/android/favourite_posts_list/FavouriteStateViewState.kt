package com.capiter.android.favourite_posts_list

import com.capiter.android.ui.base.BaseViewState

sealed class FavouriteStateViewState: BaseViewState {

    /**
     * No favorite characters to display.
     */
    object Empty :FavouriteStateViewState()

    /**
     * Favorite characters displayed.
     */
    object Listed : FavouriteStateViewState()

    // ============================================================================================
    //  Public helpers methods
    // ============================================================================================

    /**
     * Check if current view state is [Empty].
     *
     * @return True if is empty state, otherwise false.
     */
    fun isEmpty() = this is Empty

    /**
     * Check if current view state is [Listed].
     *
     * @return True if is listed state, otherwise false.
     */
    fun isListed() = this is Listed
}
