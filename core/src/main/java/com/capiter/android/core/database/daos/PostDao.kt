package com.capiter.android.core.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.capiter.android.core.database.entities.Post



@Dao
interface PostDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouritePost(post: Post)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouritePosts(posts: List<Post>)

    /**
     * Delete all database favorite posts.
     */
    @Query("DELETE FROM posts")
    suspend fun deleteAllFavouritePosts()


    /**
     * Delete database favorite post.
     */
    @Delete
    suspend fun deleteFavouritePost(favouritePost:Post)


    /**
     * Obtain database favorite posts by identifier.
     *
     * @param postId Post identifier.
     *
     * @return Favorite character if exist, otherwise null.
     */
    @Query("SELECT * FROM posts ")
    fun getFavoritePosts(): LiveData<List<Post>>



}