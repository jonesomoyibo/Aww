package com.capiter.android.core.di.components

import android.content.Context
import com.capiter.android.core.di.modules.ContextModule
import com.capiter.android.core.di.modules.DatabaseModule
import com.capiter.android.core.di.modules.NetworkModule
import dagger.Component

@Component(modules = [
    ContextModule::class,
    DatabaseModule::class,
    NetworkModule::class])
interface CoreComponent {


    fun context():Context
}