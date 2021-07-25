package com.rmarin17.nbaplayers.data.providers

import android.content.SearchRecentSuggestionsProvider

/**
 * Class used to create a simple search suggestions provider for application.
 */
class RecentSearchProvider : SearchRecentSuggestionsProvider() {

    init {
        setupSuggestions(AUTHORITY, MODE)
    }

    companion object {
        const val AUTHORITY = "com.rmarin17.nbaplayers.RecentSearchProvider"
        const val MODE: Int = DATABASE_MODE_QUERIES
    }
}
