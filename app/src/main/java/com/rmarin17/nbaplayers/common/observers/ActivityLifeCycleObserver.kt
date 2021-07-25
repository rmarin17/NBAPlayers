package com.rmarin17.nbaplayers.common.observers

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * Class responsible for observing when activity’s onCreate() is completed.
 */
class ActivityLifeCycleObserver(private val update: () -> Unit) : DefaultLifecycleObserver {
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        owner.lifecycle.removeObserver(this)
        update()
    }
}
