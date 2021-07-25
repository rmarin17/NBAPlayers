package com.rmarin17.nbaplayers.common.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Lazy
import javax.inject.Inject

/**
 * Generic class to create and inject view models.
 */
class ViewModelFactory<VM : ViewModel> @Inject constructor(private val viewModelLazy: Lazy<VM>) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = viewModelLazy.get() as T
}
