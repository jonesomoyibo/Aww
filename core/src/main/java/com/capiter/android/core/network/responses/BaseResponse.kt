package com.capiter.android.core.network.responses

class BaseResponse<T>(
    val code:Any,
    val status: String,
    val message:String,
    val data:T
)
{
}