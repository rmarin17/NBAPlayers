package com.rmarin17.nbaplayers.di

import com.rmarin17.nbaplayers.NBAApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Dagger component of the application
 */
@Component(modules = [AppModule::class, AndroidInjectionModule::class])
@Singleton
interface AppComponent : AndroidInjector<NBAApplication> {

    val homeComponentFactory: HomeComponent.Factory
}
