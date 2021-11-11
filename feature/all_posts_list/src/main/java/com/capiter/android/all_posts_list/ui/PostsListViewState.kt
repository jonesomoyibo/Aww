package com.capiter.android.all_posts_list.ui

import com.capiter.android.ui.base.BaseViewState

sealed class PostsListViewState: BaseViewState {

    /**
     * Refreshing posts list.
     */
    object Refreshing : PostsListViewState()

    /**
     * Loaded posts list.
     */
    object Loaded : PostsListViewState()

    /**
     * Loading posts list.
     */
    object Loading : PostsListViewState()

    /**
     * Loading on add more elements into posts list.
     */
    object AddLoading : PostsListViewState()

    /**
     * Empty posts list.
     */
    object Empty : PostsListViewState()

    /**
     * Error on loading posts list.
     */
    object Error : PostsListViewState()

    /**
     * Error on add more elements into posts list.
     */
    object AddError : PostsListViewState()

    /**
     * No more elements for adding into posts list.
     */
    object NoMoreElements :PostsListViewState()



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
