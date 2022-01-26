package com.capiter.android.core.network.services

import com.capiter.android.core.network.responses.BaseResponse
import com.capiter.android.core.network.responses.DataResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface AwwServices {

    @GET("top.json")
    suspend fun getAllPosts(
        @Query("t") t: String = "all",
        @Query("after") nextPage:String?,
        @Query("limit") limit: Int,
        @Query("q") query:String = ""
    ): DataResponse

    @GET("/search.json")
    fun searchForPost(
        @Query("t") t: String = "all",
        @Query("after") nextPage:String?,
        @Query("q") searchQuery:String,
        @Query("limit") limit: Int
    ): BaseResponse<DataResponse>
}