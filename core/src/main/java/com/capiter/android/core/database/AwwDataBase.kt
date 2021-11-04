package com.capiter.android.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.capiter.android.core.database.daos.PostDao
import com.capiter.android.core.database.entities.Post


@Database(
    entities = [Post::class],
    exportSchema = false,
    version = 1
)
abstract class AwwDataBase:RoomDatabase() {

    /**
     * Get  favorite posts data access object.
     *
     * @return Post favorite dao.
     */
    abstract fun postDao(): PostDao

}