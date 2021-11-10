package com.capiter.android.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "posts")
data class Post(
    @PrimaryKey
    val id:String,
    val title:String,
    val thumbnailUrl: String,
    val isVideo: Boolean)
