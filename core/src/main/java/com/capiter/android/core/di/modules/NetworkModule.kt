package com.capiter.android.core.di.modules

import com.capiter.android.core.network.repositories.AwwRepository
import com.capiter.android.core.network.services.AwwServices
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

class NetworkModule {

    @Module
    class NetworkModule(private val baseUrl:String) {

        @Provides
        @Singleton
        fun provideGson(): Gson {
            val gsonBuilder = GsonBuilder()
            gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            return gsonBuilder.create()
        }

        @Provides
        @Singleton
        fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build()
        }

        @Provides
        @Singleton
        fun provideHttpInterceptor(): HttpLoggingInterceptor {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return httpLoggingInterceptor
        }

        @Provides
        @Singleton
        fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()}


        @Provides
        @Singleton
        fun provideSwapApiService(retrofit: Retrofit): AwwServices {
            return retrofit.create(AwwServices::class.java)
        }

        @Singleton
        @Provides
        fun providePixabayRepository(service: AwwServices) = AwwRepository(service)

    }
}