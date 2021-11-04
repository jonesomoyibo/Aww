package com.capiter.android.core.database.repositories


import com.capiter.android.core.database.daos.PostDao
import com.capiter.android.core.database.entities.Post
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostsRepository @Inject constructor(private val postDao: PostDao) {


    fun insertPosts(post: Post) = postDao.insertPosts(post)

    /**
     * Delete all database favorite posts.
     */

    fun deleteAllFavouritePosts(isFavourite:Boolean) =
        postDao.deleteAllFavouritePosts(isFavourite)


    /**
     * Delete database favorite post.
     */

    fun deleteFavouritePost(isFavourite:Boolean,postId:Long) =
        postDao.deleteFavouritePost(isFavourite,postId)




    fun getFavoritePosts(isFavourite: Boolean): Flow<List<Post>> =
        postDao.getFavoritePosts(isFavourite)



    fun getAllPosts(): Flow<List<Post>> = postDao.getAllPosts()
}