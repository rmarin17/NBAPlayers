package com.rmarin17.nbaplayers.ui.navigator

import android.app.Activity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.rmarin17.nbaplayers.R
import com.rmarin17.nbaplayers.common.ext.findNavController
import com.rmarin17.nbaplayers.common.ext.navigateSafe
import com.rmarin17.nbaplayers.common.ext.performActionWithSafeActivity
import com.rmarin17.nbaplayers.ui.home.HomeActivity
import com.rmarin17.nbaplayers.ui.search.SearchFragment.Companion.SEARCH_QUERY
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * Class to handle the navigation events of home activity.
 */
class HomeNavigatorImpl @Inject constructor(activity: HomeActivity) : HomeNavigator {

    private val weakActivity = WeakReference(activity)

    private fun Activity.findHomeNavController(): NavController {
        return findNavController(R.id.nav_host_fragment)
    }

    override fun navigateToSearchQuery(query: String) {
        val bundle = bundleOf(SEARCH_QUERY to query)
        weakActivity.performActionWithSafeActivity {
            it.findHomeNavController().navigateSafe(R.id.action_searchFragment_self, bundle, null)
        }
    }
}
