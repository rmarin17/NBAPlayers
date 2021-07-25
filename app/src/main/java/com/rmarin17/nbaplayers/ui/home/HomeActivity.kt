package com.rmarin17.nbaplayers.ui.home

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import androidx.appcompat.app.AppCompatActivity
import com.rmarin17.nbaplayers.common.di.ActivityInjector
import com.rmarin17.nbaplayers.common.di.ViewModelFactory
import com.rmarin17.nbaplayers.common.ext.appComponent
import com.rmarin17.nbaplayers.common.ext.getViewModel
import com.rmarin17.nbaplayers.data.providers.RecentSearchProvider
import com.rmarin17.nbaplayers.databinding.ActivityHomeBinding
import com.rmarin17.nbaplayers.di.HomeComponent
import com.rmarin17.nbaplayers.ui.search.SearchFragment
import javax.inject.Inject

/**
 * Activity entry point.
 */
class HomeActivity : AppCompatActivity(), ActivityInjector {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<HomeViewModel>

    private val viewModel by lazy { getViewModel<HomeViewModel>(viewModelFactory) }

    private lateinit var binding: ActivityHomeBinding

    private lateinit var homeComponent: HomeComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        homeComponent = appComponent().homeComponentFactory.create(this).apply {
            inject(this@HomeActivity)
        }
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleSearchIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleSearchIntent(intent)
    }

    override fun <T> inject(fragment: T) {
        when (fragment) {
            is SearchFragment -> homeComponent.inject(fragment)
        }
    }

    private fun handleSearchIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.let { query ->
                SearchRecentSuggestions(
                    this,
                    RecentSearchProvider.AUTHORITY,
                    RecentSearchProvider.MODE
                ).saveRecentQuery(query, null)
                viewModel.navigateToSearchQuery(query)
            }
        }
    }

}
