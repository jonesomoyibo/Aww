package com.capiter.android.posts_list.ui.model

data class PostItem(
    val title:String,
    val imageUrl: String,
    val isVideo: Boolean,
    val id:String,
    val gilded:Int
)