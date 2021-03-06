package com.capiter.android.posts_list.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capiter.android.posts_list.ui.PostListViewModel
import com.capiter.android.posts_list.ui.adapter.holders.ErrorViewHolder
import com.capiter.android.posts_list.ui.adapter.holders.LoadingViewHolder
import com.capiter.android.posts_list.ui.adapter.holders.PostViewHolder
import com.capiter.android.posts_list.ui.model.PostItem
import com.capiter.android.ui.base.BaseListAdapter
import com.capiter.android.ui.base.BasePagedListAdapter
import javax.inject.Inject


/**
 * Enum class containing the different type of cell view, with the configuration.
 */
internal enum class ItemView(val type: Int) {
    POST(type = 0),
    LOADING(type = 1),
    ERROR(type = 2);

    companion object {
        fun valueOf(type: Int): ItemView = values().first { it.type == type }
    }
}

/**
 * Class for presenting posts List data in a [RecyclerView], including computing
 * diffs between Lists on a background thread.
 *
 * @see BaseListAdapter
 */
class AllPostListAdapter @Inject constructor(
    val viewModel: PostListViewModel
) : BasePagedListAdapter<PostItem>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {

    private var state: AllPostListAdapterState = AllPostListAdapterState.Added

    /**
     * Called when RecyclerView needs a new [RecyclerView.ViewHolder] of the given type to
     * represent an item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     *
     *
     *
     *
     * @param inflater Instantiates a layout XML file into its corresponding View objects.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see BaseListAdapter.onCreateViewHolder
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder =
        when (ItemView.valueOf(viewType)) {
            ItemView.POST -> PostViewHolder(inflater)
            ItemView.LOADING -> LoadingViewHolder(inflater)
            else -> ErrorViewHolder(inflater)
        }

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     * @see BaseListAdapter.onBindViewHolder
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemView(position)) {
            ItemView.POST ->
                getItem(position)?.let {
                    if (holder is PostViewHolder) {
                        holder.bind(viewModel, it)
                    }
                }
            ItemView.ERROR ->
                if (holder is ErrorViewHolder) {
                    holder.bind(viewModel)
                }

            else -> {}
        }
    }

    /**
     * Return the stable ID for the item at position.
     *
     * @param position Adapter position to query.
     * @return The stable ID of the item at position.
     * @see BasePagedListAdapter.getItemId
     */
    override fun getItemId(position: Int):Long =
        when (getItemView(position)) {
            ItemView.POST-> getItem(position)?.gilded?.toLong() ?: -3L
            ItemView.LOADING -> -1L
            ItemView.ERROR -> -2L
        }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     * @see BasePagedListAdapter.getItemCount
     */
    override fun getItemCount() =
        if (state.hasExtraRow) {
            super.getItemCount() + 1
        } else {
            super.getItemCount()
        }

    /**
     * Return the view type of the item at position for the purposes of view recycling.
     *
     * @param position Position to query.
     * @return Integer value identifying the type of the view needed to represent at position.
     * @see BasePagedListAdapter.getItemViewType
     */
    override fun getItemViewType(position: Int) = getItemView(position).type

    /**
     * Update current adapter state with the new one, applying visual changes.
     *
     * @param newState State of list adapter to update.
     */
    fun submitState(newState: AllPostListAdapterState) {
        val oldState = state
        state = newState
        if (newState.hasExtraRow && oldState != newState) {
            notifyItemChanged(itemCount - 1)
        }
    }


    /**
     * Obtain the type of view by the item position.
     *
     * @param position Current item position.
     * @return ItemView type.
     */
    private fun getItemView(position: Int) =
        if (state.hasExtraRow && position == itemCount - 1) {
            if (state.isAddError()) {
                ItemView.ERROR
            } else {
                ItemView.LOADING
            }
        }
        else {
            ItemView.POST
        }
}
