package com.capiter.android.all_posts_list.ui

import com.capiter.android.ui.base.BaseViewState

sealed class AllPostsListViewState: BaseViewState {

    /**
     * Refreshing posts list.
     */
    object Refreshing : AllPostsListViewState()

    /**
     * Loaded posts list.
     */
    object Loaded : AllPostsListViewState()

    /**
     * Loading posts list.
     */
    object Loading : AllPostsListViewState()

    /**
     * Loading on add more elements into posts list.
     */
    object AddLoading : AllPostsListViewState()

    /**
     * Empty posts list.
     */
    object Empty : AllPostsListViewState()

    /**
     * Error on loading posts list.
     */
    object Error : AllPostsListViewState()

    /**
     * Error on add more elements into posts list.
     */
    object AddError : AllPostsListViewState()

    /**
     * No more elements for adding into posts list.
     */
    object NoMoreElements :AllPostsListViewState()

    /**
     * No more elements for adding into posts list.
     */
    object AddedToFavourite :AllPostsListViewState()

    /**
     * No more elements for adding into posts list.
     */
    object AlreadyAddedToFavourite :AllPostsListViewState()

    // ============================================================================================
    //  Public helpers methods
    // ============================================================================================

    /**
     * Check if current view state is [Refreshing].
     *
     * @return True if is refreshing state, otherwise false.
     */
    fun isRefreshing() = this is Refreshing

    /**
     * Check if current view state is [Loaded].
     *
     * @return True if is loaded state, otherwise false.
     */
    fun isLoaded() = this is Loaded

    /**
     * Check if current view state is [Loading].
     *
     * @return True if is loading state, otherwise false.
     */
    fun isLoading() = this is Loading

    fun isAddToFavourite() = this is AddedToFavourite

    /**
     * Check if current view state is [AddLoading].
     *
     * @return True if is add loading state, otherwise false.
     */
    fun isAddLoading() = this is AddLoading

    /**
     * Check if current view state is [Empty].
     *
     * @return True if is empty state, otherwise false.
     */
    fun isEmpty() = this is Empty

    /**
     * Check if current view state is [Error].
     *
     * @return True if is error state, otherwise false.
     */
    fun isError() = this is Error

    /**
     * Check if current view state is [AddError].
     *
     * @return True if is add error state, otherwise false.
     */
    fun isAddError() = this is AddError

    /**
     * Check if current view state is [NoMoreElements].
     *
     * @return True if is no more elements state, otherwise false.
     */
    fun isNoMoreElements() = this is NoMoreElements
}
