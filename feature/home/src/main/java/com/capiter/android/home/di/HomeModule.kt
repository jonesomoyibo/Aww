package com.capiter.android.home.di

import com.capiter.android.core.di.scopes.FeatureScope
import com.capiter.android.home.HomeFragment
import com.capiter.android.home.HomeViewModel
import com.capiter.android.ui.extensions.viewModel
import dagger.Module
import dagger.Provides

/**
 * Class that contributes to the object graph [HomeComponent].
 *
 * @see Module
 */
@Module
class HomeModule(
    val fragment: HomeFragment
) {

    /**
     * Create a provider method binding for [HomeViewModel].
     *
     * @return Instance of view model.
     * @see Provides
     */
    @Provides
    @FeatureScope
    fun providesHomeViewModel() = fragment.viewModel {
        HomeViewModel()
    }
}
