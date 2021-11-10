package com.capiter.android.core.di.modules

import com.capiter.android.core.utils.ThemeUtils
import com.capiter.android.core.utils.ThemeUtilsImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
* Class that contributes to the object graph [CoreComponent].
*
* @see Module
*/
@Module
class UtilsModule {

    /**
     * Create a provider method binding for [ThemeUtilsImpl].
     *
     * @return Instance of theme utils.
     * @see Binds
     */
    @Singleton
    @Provides
     fun themeUtils(): ThemeUtils = ThemeUtilsImpl()



}
