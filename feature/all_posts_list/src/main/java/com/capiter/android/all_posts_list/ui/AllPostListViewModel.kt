package com.capiter.android.all_posts_list.ui


import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import com.capiter.android.all_posts_list.ui.model.PostItem
import com.capiter.android.all_posts_list.ui.model.PostItemMapper
import com.capiter.android.all_posts_list.ui.paging.PAGE_MAX_ELEMENTS
import com.capiter.android.all_posts_list.ui.paging.PostPageDataSourceFactory
import com.capiter.android.core.database.entities.Post
import com.capiter.android.core.database.repositories.FavouritePostsRepository
import com.capiter.android.core.network.NetworkState
import com.capiter.android.ui.livedata.SingleLiveData
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllPostListViewModel
@Inject constructor(
    val dataSourceFactory: PostPageDataSourceFactory,
    val favouritePostsRepository: FavouritePostsRepository
) : ViewModel() {


    private val _viewState = MutableLiveData<AllPostsListViewState>()
    val viewState: LiveData<AllPostsListViewState>
        get() = _viewState

    val networkState = Transformations.switchMap(dataSourceFactory.sourceLiveData) {
        it.networkState
    }

    val event = SingleLiveData<PostListViewEvent>()
    val data = LivePagedListBuilder(dataSourceFactory, PAGE_MAX_ELEMENTS).build()
    val state = Transformations.map(networkState) {
        when (it) {
            is NetworkState.Success ->
                if (it.isAdditional && it.isEmptyResponse) {
                    AllPostsListViewState.NoMoreElements
                } else if (it.isEmptyResponse) {
                    AllPostsListViewState.Empty
                } else {
                    AllPostsListViewState.Loaded
                }
            is NetworkState.Loading ->
                if (it.isAdditional) {
                    AllPostsListViewState.AddLoading
                } else {
                    AllPostsListViewState.Loading
                }
            is NetworkState.Error ->
                if (it.isAdditional) {
                    AllPostsListViewState.AddError
                } else {
                    AllPostsListViewState.Error
                }

        }
    }

    // ============================================================================================
    //  Public methods
    // ============================================================================================

    /**
     * Refresh characters fetch them again and update the list.
     */
    fun refreshLoadedPostList() {
        dataSourceFactory.refresh()
    }

    /**
     * Retry last fetch operation to add characters into list.
     */
    fun retryAddPostsList() {
        dataSourceFactory.retry()
    }


//    fun openPostDetail(characterId: Long) {
//        event.postValue(PostListViewEvent.OpenPostDetail(characterId))
//    }


    /**
     * Store selected character to database favorite list.
     */
    fun addPostToFavorite(postItem: PostItem) {

        viewModelScope.launch {
            favouritePostsRepository.insertPost(
                Post(postItem.id, postItem.title, postItem.thumbnailUrl, postItem.isVideo)
            )
            _viewState.postValue(AllPostsListViewState.AddedToFavourite)

        }
    }

}
