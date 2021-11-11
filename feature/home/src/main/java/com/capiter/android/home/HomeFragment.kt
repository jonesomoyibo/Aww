package com.capiter.android.home

import android.app.Application
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.annotation.RequiresApi
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.capiter.android.core.utils.CoreComponentProvider
import com.capiter.android.core.utils.ThemeUtilsImpl
import com.capiter.android.home.databinding.FragmentHomeBinding
import com.capiter.android.home.di.DaggerHomeComponent
import com.capiter.android.home.di.HomeModule
import com.capiter.android.home.menu.ToggleThemeCheckBox
import com.capiter.android.ui.base.BaseFragment
import com.capiter.android.ui.extensions.setupWithNavController
import javax.inject.Inject


/**
 * Home principal view containing bottom navigation bar with different characters tabs.
 *
 * @see BaseFragment
 */

private const val DELAY_TO_APPLY_THEME = 1000L
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    layoutId = R.layout.fragment_home
) {

    @Inject
    lateinit var themeUtils: ThemeUtilsImpl

    private val navGraphIds = listOf(
        R.navigation.navigation_post_favourite_nav_graph,
        R.navigation.navigation_post_nav_graph
    )

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param view The view returned by onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     * @see BaseFragment.onViewCreated
     */
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
            Log.d("Jones","Got here")
        }
    }

    /**
     * Called when all saved state has been restored into the view hierarchy of the fragment.
     *
     * @param savedInstanceState If the fragment is being re-created from a previous saved state,
     * this is the state.
     * @see BaseFragment.onViewStateRestored
     */
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        setupBottomNavigationBar()
    }

    /**
     * Initialize the contents of the Fragment host's standard options menu.
     *
     * @param menu The options menu in which you place your items.
     * @param inflater Inflater to instantiate menu XML files into Menu objects.
     * @see BaseFragment.onCreateOptionsMenu
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu, menu)

        menu.findItem(R.id.menu_toggle_theme).apply {
            val actionView = this.actionView
            if (actionView is ToggleThemeCheckBox) {
                actionView.isChecked = themeUtils.isDarkTheme(requireContext())
                actionView.setOnCheckedChangeListener { _, isChecked ->
                    themeUtils.setNightMode(isChecked, DELAY_TO_APPLY_THEME)
                }
            }
        }
    }

    /**
     * Initialize dagger injection dependency graph.
     */
  override fun onInitDependencyInjection() {
       DaggerHomeComponent
          .builder()
          .coreComponent(CoreComponentProvider.coreComponent(requireContext().applicationContext as Application))
           .homeModule(HomeModule(this))
         .build()
          .inject(this)
   }

    /**
     * Initialize view data binding variables.
     */
    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
    }

    // ============================================================================================
    //  Private setups methods
    // ============================================================================================

    /**
     * Configure app custom support action bar.
     */
    private fun setupToolbar() {
        setHasOptionsMenu(true)
        requireCompatActivity().setSupportActionBar(viewBinding.toolbar)
        Log.d("Jones","Tool bar,Got here")
    }

    /**
     * Configure app bottom bar via navigation graph.
     */
    @RequiresApi(Build.VERSION_CODES.S)
    private fun setupBottomNavigationBar() {
        val navController = viewBinding.bottomNavigation.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = childFragmentManager,
            containerId = R.id.nav_host_container,
            intent = requireActivity().intent
        )

        navController.observe(
            viewLifecycleOwner,
            {
                viewModel.navigationControllerChanged(it)
                setupActionBarWithNavController(requireCompatActivity(), it)
            }
        )
    }
}
