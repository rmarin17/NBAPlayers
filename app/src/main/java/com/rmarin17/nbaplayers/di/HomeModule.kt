package com.rmarin17.nbaplayers.di

import com.rmarin17.nbaplayers.domain.interactors.FetchPlayersInteractor
import com.rmarin17.nbaplayers.domain.interactors.FetchPlayersInteractorImpl
import dagger.Binds
import dagger.Module

/**
 * Module for provide dependencies related home.
 */
@Module
abstract class HomeModule {

    @Binds
    abstract fun provideFetchPlayersInteractor(fetchPlayersInteractor: FetchPlayersInteractorImpl): FetchPlayersInteractor
}
