package com.capiter.android.core.database.repositories


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.capiter.android.core.database.daos.PostDao
import com.capiter.android.core.database.entities.Post
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavouritePostsRepository @Inject constructor(val postDao: PostDao) {




    suspend fun insertPost(post: Post) = postDao.insertFavouritePost(post)

    suspend fun deleteFavouritePost(favouritePost: Post) = postDao.deleteFavouritePost(favouritePost)

    fun getFavoritePosts(): Flow<List<Post>> = postDao.getFavoritePosts()


}