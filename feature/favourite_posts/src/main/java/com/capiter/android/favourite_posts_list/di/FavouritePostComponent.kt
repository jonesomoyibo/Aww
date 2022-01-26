package com.capiter.android.favourite_posts_list.di

import com.capiter.android.core.di.components.CoreComponent
import com.capiter.android.core.di.scopes.FeatureScope
import com.capiter.android.favourite_posts_list.FavouritePostsFragment
import dagger.Component


@FeatureScope
@Component(
    modules = [FavouritePostModule::class],
    dependencies = [CoreComponent::class]
)
interface FavouritePostComponent {
    /**
     * Inject dependencies on component.
     *
     * @param favoritePostFragment Favorite component.
     */
    fun inject(favoritePostFragment: FavouritePostsFragment)
}