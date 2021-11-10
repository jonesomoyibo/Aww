package com.capiter.android.core.utils

import android.app.Application
import com.capiter.android.core.di.components.CoreComponent
import com.capiter.android.core.di.components.DaggerCoreComponent
import com.capiter.android.core.di.modules.ContextModule
import com.capiter.android.core.di.modules.DatabaseModule
import com.capiter.android.core.di.modules.NetworkModule

object CoreComponentProvider {

    private var coreCoreComponent: CoreComponent? = null





    fun  coreComponent(application:Application):CoreComponent {

        if(coreCoreComponent==null){
            return DaggerCoreComponent
                .builder()
                .databaseModule(DatabaseModule())
                .networkModule(NetworkModule("https://www.reddit.com/r/aww/"))
                .contextModule(ContextModule(application))
                .build()
        }
       return coreCoreComponent as CoreComponent

    }
}