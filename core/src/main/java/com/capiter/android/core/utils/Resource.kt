package com.capiter.android.core.utils

sealed class Resource<T>(

) {



    data class Success<T>( val data : T, val message:String?) :Resource<T>()

    data class Error<T>( val data : T?, val message:String,val isNetworkError:Boolean) :Resource<T>()

    data class Loading<T>(  val message:String?) :Resource<T>()
    /**
     * Check if current network state is [Success].
     *
     * @return True if is success state, otherwise false.
     */
    fun isSuccess() = this is Success

    /**
     * Check if current network state is [Loading].
     *
     * @return True if is loading state, otherwise false.
     */
    fun isLoading() = this is Loading

    /**
     * Check if current network state is [Error].
     *
     * @return True if is error state, otherwise false.
     */
    fun isError() = this is Error
}