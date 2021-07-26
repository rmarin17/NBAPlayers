package com.rmarin17.nbaplayers.test

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.rules.ExternalResource

/**
 * Class to define RxAndroidTrampolineRule for unit test.
 */
class RxAndroidTrampolineRule : ExternalResource() {

    override fun before() {
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    override fun after() {
        RxAndroidPlugins.setMainThreadSchedulerHandler(null)
    }
}
