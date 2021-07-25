package com.rmarin17.nbaplayers.common.ext

import androidx.appcompat.app.AppCompatActivity
import com.rmarin17.nbaplayers.NBAApplication
import com.rmarin17.nbaplayers.di.AppComponent

/**
 * File that contains all the activity extensions functions.
 */

/**
 * Extension function to get the app component.
 */
fun AppCompatActivity.appComponent(): AppComponent {
    return NBAApplication.instance.getAppComponent()
}
