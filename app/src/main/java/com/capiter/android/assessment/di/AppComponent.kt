package com.capiter.android.assessment.di

import com.capiter.android.assessment.AwwApp
import dagger.Component


@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(application: AwwApp)
}