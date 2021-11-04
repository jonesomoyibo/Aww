package com.capiter.android.core.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.capiter.android.core.database.entities.Post
import kotlinx.coroutines.flow.Flow


@Dao
interface PostDao {


    @Insert
    fun insertPosts(post: Post)

    /**
     * Delete all database favorite posts.
     */
    @Query("DELETE FROM posts where isFavourite= :isFavourite")
    fun deleteAllFavouritePosts(isFavourite:Boolean)


    /**
     * Delete database favorite post.
     */
    @Query("DELETE FROM posts where isFavourite= :isFavourite and id =:postId")
    fun deleteFavouritePost(isFavourite:Boolean,postId:Long)


    /**
     * Obtain database favorite posts by identifier.
     *
     * @param postId Post identifier.
     *
     * @return Favorite character if exist, otherwise null.
     */
    @Query("SELECT * FROM posts WHERE isFavourite = :isFavourite")
    fun getFavoritePosts(isFavourite: Boolean): Flow<List<Post>>


    @Query("SELECT * FROM posts")
    fun getAllPosts(): Flow<List<Post>>
}