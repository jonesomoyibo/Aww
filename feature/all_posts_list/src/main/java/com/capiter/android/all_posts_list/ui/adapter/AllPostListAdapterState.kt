package com.capiter.android.all_posts_list.ui.adapter


sealed class AllPostListAdapterState(
    val hasExtraRow: Boolean
) {

    /**
     * Listed the added posts into list.
     */
    object Added : AllPostListAdapterState(hasExtraRow = true)

    /**
     * Loading for new posts to add into list.
     */
    object AddLoading : AllPostListAdapterState(hasExtraRow = true)

    /**
     * Error on add new posts into list.
     */
    object AddError : AllPostListAdapterState(hasExtraRow = true)

    /**
     * No more posts to add into list.
     */
    object NoMore : AllPostListAdapterState(hasExtraRow = false)

    object Loading: AllPostListAdapterState(hasExtraRow = false)

    // ============================================================================================
    //  Public helpers methods
    // ============================================================================================

    /**
     * Check if current view state is [Added].
     *
     * @return True if is added state, otherwise false.
     */
    fun isAdded() = this is Added

    /**
     * Check if current view state is [AddLoading].
     *
     * @return True if is add loading state, otherwise false.
     */
    fun isAddLoading() = this is AddLoading

    /**
     * Check if current view state is [AddError].
     *
     * @return True if is add error state, otherwise false.
     */
    fun isAddError() = this is AddError

    /**
     * Check if current view state is [NoMore].
     *
     * @return True if is no more elements state, otherwise false.
     */
    fun isNoMore() = this is NoMore

    fun isLoading() = this is Loading
}
