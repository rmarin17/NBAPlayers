package com.rmarin17.nbaplayers.common.ext

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavOptions

/**
 * File that contains all the navigation extensions functions.
 *
 */

/**
 * Extension function to preform a save navigation (navigate only if the given destination is available).
 */
fun NavController.navigateSafe(@IdRes resId: Int, bundle: Bundle? = null, navOptions: NavOptions?) {
    currentDestination?.getAction(resId)?.let {
        navigate(resId, bundle, navOptions)
    }
}
