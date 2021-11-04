package com.capiter.android.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "posts")
data class Post(
    @PrimaryKey
    val id:Int,
    val title:String,
    val isFavourite:Boolean,
    val imageUrl:String,
    val iconUrl:String)
