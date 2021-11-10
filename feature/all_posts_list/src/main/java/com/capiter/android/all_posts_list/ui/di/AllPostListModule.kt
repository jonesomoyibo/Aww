package com.capiter.android.all_posts_list.ui.di

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.viewModelScope
import com.capiter.android.all_posts_list.ui.AllPostListFragment
import com.capiter.android.all_posts_list.ui.AllPostListViewModel
import com.capiter.android.all_posts_list.ui.adapter.AllPostListAdapter
import com.capiter.android.all_posts_list.ui.model.PostItemMapper
import com.capiter.android.all_posts_list.ui.paging.PostPageDataSource
import com.capiter.android.all_posts_list.ui.paging.PostPageDataSourceFactory
import com.capiter.android.core.database.repositories.FavouritePostsRepository
import com.capiter.android.core.di.scopes.FeatureScope
import com.capiter.android.core.network.repositories.AllPostListRepository
import com.capiter.android.ui.extensions.viewModel
import dagger.Module
import dagger.Provides
@Module
class AllPostListModule (

    val fragment: AllPostListFragment
) {

    /**
     * Create a provider method binding for [CharactersListViewModel].
     *
     * @param dataFactory Data source factory for characters.
     * @return Instance of view model.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesPostListViewModel(
        dataFactory: PostPageDataSourceFactory,
        favouritePostsRepository: FavouritePostsRepository
    ) = fragment.viewModel {
        AllPostListViewModel(dataFactory,favouritePostsRepository)
    }

    /**
     * Create a provider method binding for [CharactersPageDataSource].
     *
     * @return Instance of data source.
     * @see Provides
     */
    @Provides
    fun providesPostsPageDataSource(
        viewModel: AllPostListViewModel,
        repository: AllPostListRepository,
        mapper: PostItemMapper
    ) = PostPageDataSource(
        repository = repository,
        scope = viewModel.viewModelScope,
        mapper = mapper
    )

    /**
     * Create a provider method binding for [CharacterItemMapper].
     *
     * @return Instance of mapper.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesPostItemMapper() = PostItemMapper()

    /**
     * Create a provider method binding for [PostsListAdapter].
     *
     * @return Instance of adapter.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesCharactersListAdapter(
        viewModel: AllPostListViewModel
    ) = AllPostListAdapter(viewModel)




}
