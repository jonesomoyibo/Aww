package com.capiter.android.assessment.di

import android.content.Context
import com.capiter.android.assessment.AwwApp
import dagger.Module
import dagger.Provides

@Module
class AppModule {


    @Provides
    fun provideContext(application: AwwApp): Context = application.applicationContext
}