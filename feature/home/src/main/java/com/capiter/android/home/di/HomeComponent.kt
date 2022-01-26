package com.capiter.android.home.di

import com.capiter.android.posts_list.ui.di.AllPostListComponent
import com.capiter.android.core.di.components.CoreComponent
import com.capiter.android.core.di.scopes.HomeScope
import com.capiter.android.home.HomeFragment
import dagger.Component


@HomeScope
@Component(
    modules = [HomeModule::class],
    dependencies = [CoreComponent::class]

)
interface HomeComponent {



    /**
     * Inject dependencies on component.
     *
     * @param homeFragment Home component.
     */
    fun inject(homeFragment: HomeFragment)
}