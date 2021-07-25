package com.rmarin17.nbaplayers

import android.app.Application
import com.rmarin17.nbaplayers.di.AppComponent
import com.rmarin17.nbaplayers.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * Application class for maintaining global application state.
 */
class NBAApplication : Application(), HasAndroidInjector {

    private lateinit var appComponent: AppComponent

    @Inject
    internal lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.create()
        appComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    fun getAppComponent(): AppComponent {
        return appComponent
    }

    companion object {
        lateinit var instance: NBAApplication
            private set

        fun applicationContext(): NBAApplication {
            return instance
        }
    }
}
