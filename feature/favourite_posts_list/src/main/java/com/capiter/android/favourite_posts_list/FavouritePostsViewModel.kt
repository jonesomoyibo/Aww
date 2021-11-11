package com.capiter.android.favourite_posts_list

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.*
import com.capiter.android.core.database.entities.Post
import com.capiter.android.core.database.repositories.FavouritePostsRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouritePostsViewModel  @Inject constructor(
    private val favouritePostsRepository: FavouritePostsRepository
) : ViewModel() {

     var data:MutableLiveData<List<Post>> = MutableLiveData()
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




     fun fetchData() {

        viewModelScope.launch {
            favouritePostsRepository.getFavoritePosts().collect {
                data.postValue(it)
            }
        }

        }

    }

