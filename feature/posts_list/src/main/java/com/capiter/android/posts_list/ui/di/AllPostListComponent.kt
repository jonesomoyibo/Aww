package com.capiter.android.posts_list.ui.di

import com.capiter.android.posts_list.ui.PostListFragment
import com.capiter.android.posts_list.ui.PostListViewModel
import com.capiter.android.core.di.components.CoreComponent
import com.capiter.android.core.di.scopes.FeatureScope
import dagger.Component


@Component(
    modules = [AllPostListModule::class],
    dependencies = [CoreComponent::class]
)
@FeatureScope
interface AllPostListComponent {
    /**
     * Inject dependencies on component.
     *
     * @param favoriteFragment Favorite component.
     */
    fun inject(postListFragment: PostListFragment)


    /**
     * provides this dependency to other component(s).
     *
     * @return postListViewModel.
     */
    fun postListViewModel(): PostListViewModel
}