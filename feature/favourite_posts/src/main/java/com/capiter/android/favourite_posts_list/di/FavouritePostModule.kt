package com.capiter.android.favourite_posts_list.di

import com.capiter.android.core.database.repositories.FavouritePostsRepository
import com.capiter.android.core.di.scopes.FeatureScope
import com.capiter.android.favourite_posts_list.FavouritePostsFragment
import com.capiter.android.favourite_posts_list.FavouritePostsViewModel
import com.capiter.android.favourite_posts_list.adapter.FavouritePostsAdapter
import com.capiter.android.ui.extensions.viewModel
import dagger.Module
import dagger.Provides


@Module
class FavouritePostModule(
    val fragment: FavouritePostsFragment
) {

    /**
     * Create a provider method binding for [FavoritePostViewModel].
     *
     * @param FavoritePostsRepository Handler character favorite repository.
     * @return Instance of view model.
     * @see Provides
     */
    @Provides
    @FeatureScope
    fun providesFavouritePostsViewModel(
        favouriteRepository: FavouritePostsRepository
    ) = fragment.viewModel{
        FavouritePostsViewModel(
            favouritePostsRepository =  favouriteRepository
        )
    }

    /**
     * Create a provider method binding for [FavoritePostsAdapter].
     *
     * @return Instance of adapter.
     * @see Provides
     */
    @Provides
    @FeatureScope
    fun providesFavoritePostAdapter() = FavouritePostsAdapter()
}
