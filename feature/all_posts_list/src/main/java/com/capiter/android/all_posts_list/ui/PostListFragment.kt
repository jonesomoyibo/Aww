package com.capiter.android.all_posts_list.ui

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capiter.android.all_posts_list.R
import com.capiter.android.all_posts_list.databinding.FragmentPostsListBinding
import com.capiter.android.all_posts_list.ui.adapter.AllPostListAdapter
import com.capiter.android.all_posts_list.ui.adapter.AllPostListAdapterState
import com.capiter.android.all_posts_list.ui.di.AllPostListModule
import com.capiter.android.all_posts_list.ui.di.DaggerAllPostListComponent
import com.capiter.android.all_posts_list.ui.model.PostItem
import com.capiter.android.core.database.entities.Post
import com.capiter.android.core.utils.CoreComponentProvider
import com.capiter.android.ui.base.BaseFragment
import com.capiter.android.ui.extensions.gridLayoutManager
import com.capiter.android.ui.extensions.linearLayoutManager
import com.capiter.android.ui.extensions.observe
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class PostListFragment : BaseFragment<FragmentPostsListBinding, PostListViewModel>
    (R.layout.fragment_posts_list) {

    @Inject
    lateinit var viewAdapter: AllPostListAdapter

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param view The view returned by onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     * @see BaseFragment.onViewCreated
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.state, ::onViewStateChange)
        observe(viewModel.data, ::onViewDataChange)
        observe(viewModel.favouriteIconViewState, ::onFavouriteIconViewStateChange)
        observe(viewModel.addToFavouritePostsEvent, ::onViewEventChange)
    }

    /**
     * Initialize dagger injection dependency graph.
     */
    override fun onInitDependencyInjection() {
        DaggerAllPostListComponent
            .builder()
            .coreComponent(CoreComponentProvider.coreComponent(requireContext().applicationContext as Application))
            .allPostListModule(AllPostListModule(this))
            .build()
            .inject(this)
    }

    /**
     * Initialize view data binding variables.
     */
    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
        viewBinding.includeList.postsList.apply {
            adapter = viewAdapter
            layoutManager = gridLayoutManager

        }
    }

    // ============================================================================================
    //  Private observers methods
    // ============================================================================================

    /**
     * Observer view data change on [AllPostsListViewModel].
     *
     * @param viewData Paged list of characters.
     */
    private fun onViewDataChange(viewData: PagedList<PostItem>) {
        viewAdapter.submitList(viewData)
    }

    /**
     * Observer view state change on [AllPostsListViewModel].
     *
     * @param viewState State of posts list.
     */
    private fun onViewStateChange(viewState: PostsListViewState) {
        when (viewState) {
            is PostsListViewState.Loaded ->
                viewAdapter.submitState(AllPostListAdapterState.Added)
            is PostsListViewState.Loading ->
                viewAdapter.submitState(AllPostListAdapterState.Loading)
            is PostsListViewState.AddLoading ->
                viewAdapter.submitState(AllPostListAdapterState.AddLoading)
            is PostsListViewState.AddError ->
                viewAdapter.submitState(AllPostListAdapterState.AddError)
            is PostsListViewState.NoMoreElements ->
                viewAdapter.submitState(AllPostListAdapterState.NoMore)

            else -> {
            }
        }
    }

    /**
     * Observer view event change on [CharactersListViewModel].
     *
     * @param viewEvent Event on characters list.
     */
    private fun onViewEventChange(event: AddPostToFavouriteEvent) {
        when (event) {
            is AddPostToFavouriteEvent.AddPostToFavourites -> {
                val postItem = event.post
                viewModel.addPostToFavourites(
                    Post(
                        postItem.id,
                        postItem.title,
                        postItem.imageUrl,
                        postItem.isVideo
                    )
                )
            }
        }
    }


/**
 * Observer view state change on [AllPostsListViewModel].
 *
 * @param viewState State of posts list.
 */
private fun onFavouriteIconViewStateChange(favouriteIconViewState: FavouriteIconViewState) {
    when (favouriteIconViewState) {
        is FavouriteIconViewState.AddedToFavorite -> {
            Snackbar.make(
                requireView(),
                "Post added to favourite.",
                Snackbar.LENGTH_LONG
            ).show()
        }

        is FavouriteIconViewState.AlreadyAddedToFavorite -> {
            Snackbar.make(
                requireView(),
                "Post already added to favourite.",
                Snackbar.LENGTH_LONG
            ).show()
        }


        else -> {}
    }
}


}

