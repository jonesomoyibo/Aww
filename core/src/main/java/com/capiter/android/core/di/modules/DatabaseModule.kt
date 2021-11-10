package com.capiter.android.core.di.modules

import android.content.Context
import androidx.room.Room
import com.capiter.android.core.database.AwwDataBase
import com.capiter.android.core.database.daos.PostDao
import com.capiter.android.core.database.migrations.MIGRATION_1_2
import com.capiter.android.core.database.repositories.FavouritePostsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    /**
     * Create a provider method binding for [MarvelDatabase].
     *
     * @return Instance of marvel database.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideMarvelDatabase(context: Context) =
        Room.databaseBuilder(
            context,
            AwwDataBase::class.java,
           "AwwDb"
        ).addMigrations(MIGRATION_1_2)
            .build()

    /**
     * Create a provider method binding for [CharacterFavoriteDao].
     *
     * @return Instance of character favorite data access object.
     * @see Provides
     */
    @Singleton
    @Provides
    fun providePostDao(awwDatabase: AwwDataBase) =
        awwDatabase.postDao()

    /**
     * Create a provider method binding for [FavoritePostRepository].
     *
     * @return Instance of character favorite repository.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideFavouriteRepository(
        postDao: PostDao
    ) = FavouritePostsRepository(postDao)
}