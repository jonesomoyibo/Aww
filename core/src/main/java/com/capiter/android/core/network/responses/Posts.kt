package com.capiter.android.core.network.responses

import com.google.gson.annotations.SerializedName

data class Posts(
    val kind:String,
    @SerializedName("data")
    val subPost:Post
)
