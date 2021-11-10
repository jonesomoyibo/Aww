package com.capiter.android.core.utils


import com.capiter.android.core.network.responses.BaseResponse
import retrofit2.HttpException
import java.io.IOException

abstract class BaseRepository {


    fun<T> safeApiCall(apiCall: () -> T):Resource<T>{
     return try {
          Resource.Success(apiCall.invoke(),"Posts fetched successfully")
      }
      catch (throwable:Throwable){
          when(throwable){
           is HttpException ->{
               Resource.Error(null,message = throwable.message(),isNetworkError = false)
           }

           is IOException -> {
               Resource.Error(null,message = "Check your internet connection",isNetworkError = true)
           }
              else -> {
                  Resource.Error(null,"Something went wrong",isNetworkError =false)
              }
          }
      }
    }
}