package com.capiter.android.core.database.repositories


import androidx.lifecycle.LiveData
import com.capiter.android.core.database.daos.PostDao
import com.capiter.android.core.database.entities.Post
import javax.inject.Inject

class FavouritePostsRepository @Inject constructor(val postDao: PostDao) {


    suspend fun insertPosts(posts: List<Post>) = postDao.insertFavouritePosts(posts)

    suspend fun insertPost(post: Post) = postDao.insertFavouritePost(post)

    suspend fun deleteAllFavouritePosts() = postDao.deleteAllFavouritePosts()

    suspend fun deleteFavouritePost(favouritePost: Post) = postDao.deleteFavouritePost(favouritePost)

    fun getFavoritePosts(): LiveData<List<Post>> = postDao.getFavoritePosts()


}