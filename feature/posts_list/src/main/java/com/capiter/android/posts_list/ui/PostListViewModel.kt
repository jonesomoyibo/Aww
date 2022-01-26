package com.capiter.android.posts_list.ui


import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.capiter.android.posts_list.ui.model.PostItem
import com.capiter.android.posts_list.ui.paging.PAGE_MAX_ELEMENTS
import com.capiter.android.posts_list.ui.paging.PostPageDataSourceFactory
import com.capiter.android.core.database.entities.Post
import com.capiter.android.core.database.repositories.FavouritePostsRepository
import com.capiter.android.core.network.NetworkState
import com.capiter.android.ui.livedata.SingleLiveData
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostListViewModel
@Inject constructor(
    private val dataSourceFactory: PostPageDataSourceFactory,
    private val favouritePostsRepository: FavouritePostsRepository
) : ViewModel() {


    private val _favouriteIconViewState = SingleLiveData<FavouriteIconViewState>()
    val favouriteIconViewState: LiveData<FavouriteIconViewState>
        get() = _favouriteIconViewState

     val addToFavouritePostsEvent = SingleLiveData<AddPostToFavouriteEvent>()




    private val networkState = Transformations.switchMap(dataSourceFactory.sourceLiveData) {
        it.networkState
    }

    val data = LivePagedListBuilder(dataSourceFactory, PagedList.Config.Builder().setPageSize(PAGE_MAX_ELEMENTS).build()).build()
    val state = Transformations.map(networkState) {
        when (it) {
            is NetworkState.Success ->
                if (it.isAdditional && it.isEmptyResponse) {
                    PostsListViewState.NoMoreElements
                } else if (it.isEmptyResponse) {
                    PostsListViewState.Empty
                } else {
                    PostsListViewState.Loaded
                }
            is NetworkState.Loading ->
                if (it.isAdditional) {
                    PostsListViewState.AddLoading
                } else {
                    PostsListViewState.Loading
                }
            is NetworkState.Error ->
                if (it.isAdditional) {
                    PostsListViewState.AddError
                } else {
                    PostsListViewState.Error
                }

        }
    }

    // ============================================================================================
    //  Public methods
    // ============================================================================================

    /**
     * Refresh posts fetch them again and update the list.
     */
    fun refreshLoadedPostList() {
        dataSourceFactory.refresh()
    }

    /**
     * Retry last fetch operation to add posts into list.
     */
    fun retryAddPostsList() {
        dataSourceFactory.retry()
    }



    /**
     * emit AddPostToFavouriteEvent
     */
    fun emitAddPostToFavoriteEvent(postItem: PostItem) {
        addToFavouritePostsEvent.postValue(AddPostToFavouriteEvent.AddPostToFavourites(postItem))

    }


    /**
     * Saves post to db
     */
    fun addPostToFavourites(post:Post){

        viewModelScope.launch {
            favouritePostsRepository.insertPost(post)
            _favouriteIconViewState.postValue(FavouriteIconViewState.AddedToFavorite)
        }

    }


   fun searchForPost(query:String){
       dataSourceFactory.searchForPost(query)

   }

}
