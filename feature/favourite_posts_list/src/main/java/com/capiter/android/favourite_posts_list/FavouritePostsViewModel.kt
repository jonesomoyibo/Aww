package com.capiter.android.favourite_posts_list

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capiter.android.core.database.entities.Post
import com.capiter.android.core.database.repositories.FavouritePostsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouritePostsViewModel  @Inject constructor(
    val favouritePostsRepository: FavouritePostsRepository
) : ViewModel() {

    val data = favouritePostsRepository.getFavoritePosts()
    val state = Transformations.map(data) {
        if (it.isEmpty()) {
            FavouriteStateViewState.Empty
        } else {
            FavouriteStateViewState.Listed
        }
    }


    fun removeFavoritePost(favouritePost: Post) {
        viewModelScope.launch {
            favouritePostsRepository.deleteFavouritePost(favouritePost)
        }
    }

    fun removeAllFavoritePost() {
        viewModelScope.launch {
            favouritePostsRepository.deleteAllFavouritePosts()
        }
    }



 fun getAllPosts(): LiveData<List<Post>> = favouritePostsRepository.getFavoritePosts()


}
