package com.capiter.android.favourite_posts_list

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import com.capiter.android.core.database.entities.Post
import com.capiter.android.core.utils.CoreComponentProvider
import com.capiter.android.favourite_posts_list.adapter.FavouritePostsAdapter
import com.capiter.android.favourite_posts_list.adapter.PostFavouriteTouchHelper
import com.capiter.android.favourite_posts_list.databinding.FragmentFavouritePostsBinding
import com.capiter.android.favourite_posts_list.di.DaggerFavouritePostComponent
import com.capiter.android.favourite_posts_list.di.FavouritePostModule
import com.capiter.android.ui.base.BaseFragment
import com.capiter.android.ui.extensions.observe
import javax.inject.Inject

class FavouritePostsFragment :
    BaseFragment<FragmentFavouritePostsBinding, FavouritePostsViewModel>(
        layoutId = R.layout.fragment_favourite_posts
    ) {

    @Inject
    lateinit var viewAdapter: FavouritePostsAdapter

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
        observe(viewModel.data, ::onViewDataChange)
    }

    /**
     * Initialize dagger injection dependency graph.
     */
    override fun onInitDependencyInjection() {
        DaggerFavouritePostComponent
            .builder()
            .coreComponent(CoreComponentProvider.coreComponent(requireContext().applicationContext as Application))
            .favouritePostModule(FavouritePostModule(this))
            .build()
            .inject(this)
    }

    /**
     * Initialize view data binding variables.
     */
    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
        viewBinding.includeList.recyclerView.apply {
            adapter = viewAdapter

            ItemTouchHelper(
                PostFavouriteTouchHelper {
                    viewModel.removeFavoritePost(viewAdapter.currentList[it])
                }
            ).attachToRecyclerView(this)
        }
    }

    // ============================================================================================
    //  Private observers methods
    // ============================================================================================

    /**
     * Observer view data change on [FavoritePostsViewModel].
     *
     * @param viewData List of favorite characters.
     */
    private fun onViewDataChange(viewData: List<Post>) {
        viewAdapter.submitList(viewData)
    }
}
