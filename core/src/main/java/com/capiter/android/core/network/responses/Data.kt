package com.capiter.android.core.network.responses

import com.google.gson.annotations.SerializedName

data class Data(
    val after: String,
    val before: Any?,
    @SerializedName("children")
    val posts: List<Post>,
    @SerializedName("dist")
    val count: Int,
    val geo_filter: String,
    val modhash: String
) {
}