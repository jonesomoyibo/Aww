package com.capiter.android.all_posts_list.ui.di

import androidx.lifecycle.viewModelScope
import com.capiter.android.all_posts_list.ui.PostListFragment
import com.capiter.android.all_posts_list.ui.PostListViewModel
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

    val fragment: PostListFragment
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
        PostListViewModel(dataFactory,favouritePostsRepository)
    }

    /**
     * Create a provider method binding for [CharactersPageDataSource].
     *
     * @return Instance of data source.
     * @see Provides
     */
    @Provides
    fun providesPostsPageDataSource(
        viewModel: PostListViewModel,
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
        viewModel: PostListViewModel
    ) = AllPostListAdapter(viewModel)




}
