package com.rmarin17.nbaplayers.ui.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rmarin17.nbaplayers.R
import com.rmarin17.nbaplayers.common.di.ViewModelFactory
import com.rmarin17.nbaplayers.common.ext.activityInjector
import com.rmarin17.nbaplayers.common.ext.getViewModel
import com.rmarin17.nbaplayers.common.ext.observe
import com.rmarin17.nbaplayers.common.ext.visible
import com.rmarin17.nbaplayers.common.observers.ActivityLifeCycleObserver
import com.rmarin17.nbaplayers.databinding.FragmentSearchBinding
import com.rmarin17.nbaplayers.ui.adapters.ListPlayersAdapter
import com.rmarin17.nbaplayers.ui.models.PlayerUiModel
import javax.inject.Inject

/**
 * Fragment to search players.
 */
class SearchFragment : Fragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<PlayersSearchViewModel>

    private val viewModel by lazy { getViewModel<PlayersSearchViewModel>(viewModelFactory) }

    private lateinit var binding: FragmentSearchBinding

    private lateinit var playersAdapter: ListPlayersAdapter

    private val searchQuery by lazy { arguments?.getString(SEARCH_QUERY) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().lifecycle.addObserver(ActivityLifeCycleObserver {
            activityInjector().inject(this)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpObservers()
        lifecycle.addObserver(viewModel)
        searchQueryOrGetPlayers()
    }

    private fun setUpView() {
        with(binding) {
            playersAdapter = ListPlayersAdapter(mutableListOf(), ::onClickItem)
            productsRecycler.layoutManager = LinearLayoutManager(context)
            productsRecycler.adapter = playersAdapter
            val searchManager =
                requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
            searchView.apply {
                setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
                queryHint = resources.getString(R.string.query_hint_search)
                isQueryRefinementEnabled = true
            }
            searchQuery?.let {
                searchView.setQuery(it, false)
            }
        }
    }

    private fun onSearchState(state: PlayerSearchState) {
        when (state) {
            is PlayerSearchState.Loading -> {
                showHideLoadingView(true)
                state.loadedAction.invoke()
            }
            is PlayerSearchState.PlayerResultSuccess -> {
                showHideLoadingView(false)
                if (state.players.isNotEmpty()) {
                    playersAdapter.updatePlayerList(state.players)
                } else {
                    binding.emptyView.visible()
                }
            }
            is PlayerSearchState.PlayerResultFailure -> showFailureErrorView()
        }
    }

    private fun searchQueryOrGetPlayers() {
        searchQuery?.let {
            viewModel.searchProductsByQuery(it)
        } ?: viewModel.getAllPlayers()
    }

    private fun onClickItem(player: PlayerUiModel) {
        // TODO - Add navigation to player detail.
    }

    private fun showHideLoadingView(isVisible: Boolean) {
        binding.emptyView.visible(false)
        binding.errorView.visible(false)
        binding.progressBar.visible(isVisible)
        binding.productsRecycler.visible(!isVisible)
    }

    private fun showFailureErrorView() {
        showHideLoadingView(false)
        binding.errorView.visible()
        binding.errorView.errorViewRetryButton.setOnClickListener {
            searchQueryOrGetPlayers()
        }
    }

    private fun setUpObservers() {
        observe(viewModel.playersSearchState, ::onSearchState)
    }

    companion object {
        const val SEARCH_QUERY = "search_query"
    }

}
