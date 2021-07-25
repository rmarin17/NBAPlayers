package com.rmarin17.nbaplayers.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rmarin17.nbaplayers.common.di.ActivityInjector
import com.rmarin17.nbaplayers.common.ext.appComponent
import com.rmarin17.nbaplayers.databinding.ActivityHomeBinding
import com.rmarin17.nbaplayers.di.HomeComponent
import com.rmarin17.nbaplayers.ui.search.SearchFragment

/**
 * Activity entry point.
 */
class HomeActivity : AppCompatActivity(), ActivityInjector {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var homeComponent: HomeComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        homeComponent = appComponent().homeComponentFactory.create(this).apply {
            inject(this@HomeActivity)
        }
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun <T> inject(fragment: T) {
        when (fragment) {
            is SearchFragment -> homeComponent.inject(fragment)
        }
    }
}
