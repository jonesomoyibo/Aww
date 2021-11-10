package com.capiter.android.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

val NAV_FRAGMENTS_ID = setOf(R.id.favouritePostsFragment, R.id.allPostListFragment)

/**
 * View model responsible for preparing and managing the data for [HomeFragment].
*
 * @see ViewModel
 */
class HomeViewModel : ViewModel() {
   private val _state = MutableLiveData<HomeViewState>()
   val state: LiveData<HomeViewState>
       get() = _state

   /**
   * Navigation controller add destination changed listener.
    *
   * @param navController Navigation manager.
   */
   fun navigationControllerChanged(navController: NavController) {
       navController.addOnDestinationChangedListener { _, destination, _ ->
            if (NAV_FRAGMENTS_ID.contains(destination.id)) {
               _state.postValue(HomeViewState.NavigationScreen)
         } else {
               _state.postValue(HomeViewState.FullScreen)
           }
      }
   }
}