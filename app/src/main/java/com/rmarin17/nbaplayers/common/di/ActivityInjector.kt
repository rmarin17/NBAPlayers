package com.rmarin17.nbaplayers.common.di

/**
 * Generic interface for activity injector.
 */
interface ActivityInjector {

    // Must be called from onActivityCrated method inside fragment
    fun <T> inject(fragment: T)
}
