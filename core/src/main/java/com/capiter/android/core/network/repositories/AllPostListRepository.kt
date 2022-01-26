package com.capiter.android.core.network.repositories

import com.capiter.android.core.network.responses.BaseResponse
import com.capiter.android.core.network.responses.DataResponse
import com.capiter.android.core.network.services.AwwServices


import javax.inject.Inject

class AllPostListRepository @Inject constructor(val awwservice:AwwServices){

    suspend fun getAllPosts(nextPage:String,limit:Int,searchQuery:String): DataResponse =

            awwservice.getAllPosts(
                nextPage = nextPage,
                limit = limit,
                query = searchQuery
            )

}