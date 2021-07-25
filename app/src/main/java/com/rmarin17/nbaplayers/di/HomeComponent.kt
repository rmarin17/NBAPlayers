package com.rmarin17.nbaplayers.di

import com.rmarin17.nbaplayers.common.di.ActivityScope
import com.rmarin17.nbaplayers.ui.home.HomeActivity
import dagger.BindsInstance
import dagger.Subcomponent

/**
 * Dagger sub component for home.
 */
@ActivityScope
@Subcomponent(modules = [HomeModule::class])
interface HomeComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: HomeActivity): HomeComponent
    }

    fun inject(activity: HomeActivity)
}
