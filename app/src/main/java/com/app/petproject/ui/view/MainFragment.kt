package com.app.petproject.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.petproject.R
import com.app.petproject.databinding.MainFragmentBinding
import com.app.petproject.paging.LoaderAdapter
import com.app.petproject.paging.MoviePageAdapter
import com.app.petproject.ui.viewmodel.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainFragmentViewModel by viewModels()

    private val moviesAdapter by lazy(LazyThreadSafetyMode.NONE) {
        MoviePageAdapter()
    }

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovies()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //initialize recycler view
        val layoutManagerRV = LinearLayoutManager(requireContext())
        binding.recyclerView.apply {
            layoutManager = layoutManagerRV
            setHasFixedSize(true)
            adapter = moviesAdapter
        }
        with(binding) {
            //show retry button
            swipeRefresh.setOnRefreshListener { moviesAdapter.refresh() }
            //show progress bar in footer and header
            recyclerView.adapter = moviesAdapter.withLoadStateHeaderAndFooter(
                header = LoaderAdapter(moviesAdapter),
                footer = LoaderAdapter(moviesAdapter)
            )
        }

        //starter progress bar
        moviesAdapter.addLoadStateListener { state ->
            with(binding) {
                //display recyclerView when don't loading
                recyclerView.isVisible = state.refresh != LoadState.Loading
                //update state progress bar and refresh when doing update
                containerLouder.isVisible = state.refresh == LoadState.Loading
                swipeRefresh.isRefreshing = state.refresh == LoadState.Loading
            }
        }

        //get id film
        //start overview fragment and send id
        moviesAdapter.onItemClick = {
            val fragment = OverviewFragment()
            val bundle = Bundle()
            bundle.putInt("id", it)
            fragment.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frame, fragment)
                .addToBackStack(null)
                .commit()
        }

        //update adapter data from api
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.movie?.collectLatest { pagingData ->
                moviesAdapter.submitData(pagingData)
            }
        }


    }


}