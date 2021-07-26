package com.rmarin17.nbaplayers

import io.reactivex.rxjava3.schedulers.TestScheduler

/**
 * File that contains all the util function related to uni test.
 */

/**
 * Function to trigger the testScheduler.
 */
infix fun <T> (() -> T).andTrigger(testScheduler: TestScheduler) {
    this.invoke()
    testScheduler.triggerActions()
}
