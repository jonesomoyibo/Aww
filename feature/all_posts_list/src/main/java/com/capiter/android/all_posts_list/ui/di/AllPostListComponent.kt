package com.capiter.android.all_posts_list.ui.di

import com.capiter.android.all_posts_list.ui.PostListFragment
import com.capiter.android.core.di.components.CoreComponent
import com.capiter.android.core.di.scopes.FeatureScope
import dagger.Component


@Component(modules = [AllPostListModule::class],
dependencies = [CoreComponent::class])
@FeatureScope
interface AllPostListComponent{
/**
 * Inject dependencies on component.
 *
 * @param favoriteFragment Favorite component.
 */
fun inject(postListFragment: PostListFragment)
}