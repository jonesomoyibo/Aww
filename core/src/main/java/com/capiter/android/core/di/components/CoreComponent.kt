package com.capiter.android.core.di.components

import android.content.Context
import com.capiter.android.core.database.repositories.FavouritePostsRepository
import com.capiter.android.core.di.modules.ContextModule
import com.capiter.android.core.di.modules.DatabaseModule
import com.capiter.android.core.di.modules.NetworkModule
import com.capiter.android.core.network.repositories.AllPostListRepository
import com.capiter.android.core.utils.ThemeUtils
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [
    ContextModule::class,
    DatabaseModule::class,
    NetworkModule::class])
interface CoreComponent {

    fun context():Context
    fun postsRepository():AllPostListRepository
    fun favouritePostsRepository():FavouritePostsRepository
//    fun themeUtils(): ThemeUtils
}