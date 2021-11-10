package com.capiter.android.all_posts_list.ui

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.paging.PagedList
import com.capiter.android.all_posts_list.R
import com.capiter.android.all_posts_list.databinding.FragmentPostsListBinding
import com.capiter.android.all_posts_list.ui.adapter.AllPostListAdapter
import com.capiter.android.all_posts_list.ui.adapter.AllPostListAdapterState
import com.capiter.android.all_posts_list.ui.di.AllPostListModule
import com.capiter.android.all_posts_list.ui.di.DaggerAllPostListComponent
import com.capiter.android.all_posts_list.ui.model.PostItem
import com.capiter.android.core.utils.CoreComponentProvider
import com.capiter.android.ui.base.BaseFragment
import com.capiter.android.ui.extensions.linearLayoutManager
import com.capiter.android.ui.extensions.observe
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class AllPostListFragment : BaseFragment<FragmentPostsListBinding,AllPostListViewModel>
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
//        observe(viewModel.event, ::onViewEvent)
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
            layoutManager = linearLayoutManager
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
    private fun onViewStateChange(viewState: AllPostsListViewState) {
        when (viewState) {
            is AllPostsListViewState.Loaded ->
                viewAdapter.submitState(AllPostListAdapterState.Added)
            is AllPostsListViewState.Loading->
                viewAdapter.submitState(AllPostListAdapterState.Loading)
            is AllPostsListViewState.AddLoading ->
                viewAdapter.submitState(AllPostListAdapterState.AddLoading)
            is AllPostsListViewState.AddError ->
                viewAdapter.submitState(AllPostListAdapterState.AddError)
            is AllPostsListViewState.NoMoreElements ->
                viewAdapter.submitState(AllPostListAdapterState.NoMore)
            is AllPostsListViewState.AddedToFavourite  -> {
                Snackbar.make(
                    requireView(),
                    "Post added to favourite.",
                    Snackbar.LENGTH_LONG
                ).show()
            }

            else -> {}
        }
    }
//
//    /**
//     * Observer view event change on [CharactersListViewModel].
//     *
//     * @param viewEvent Event on characters list.
//     */
//    private fun onViewEvent(viewEvent: PostListViewEvent) {
//        when (viewEvent) {
//            is PostListViewEvent.OpenCharacterDetail ->
//                findNavController().navigate(
//                    CharactersListFragmentDirections
//                        .actionCharactersListFragmentToCharacterDetailFragment(viewEvent.id)
//                )
//        }
//    }
}

