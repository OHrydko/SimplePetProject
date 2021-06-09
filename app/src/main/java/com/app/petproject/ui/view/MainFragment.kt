package com.app.petproject.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.petproject.R
import com.app.petproject.adapter.MovieAdapter
import com.app.petproject.databinding.MainFragmentBinding
import com.app.petproject.entiti.Resource
import com.app.petproject.ui.viewmodel.MainFragmentViewModel
import com.app.petproject.utils.gone
import com.app.petproject.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainFragmentViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovie()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        movieAdapter = MovieAdapter(ArrayList())

        val layoutManagerRV = LinearLayoutManager(requireContext())
        binding.recyclerView.apply {
            layoutManager = layoutManagerRV
            setHasFixedSize(true)
            adapter = movieAdapter
        }

        movieAdapter.onItemClick = {
            val fragment = OverviewFragment()
            val bundle = Bundle()
            bundle.putInt("id", it)
            fragment.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frame, fragment)
                .addToBackStack(null)
                .commit()
        }

        updateUI()

    }

    private fun updateUI() {
        viewModel.response.observe(requireActivity(), {
            if (it.data != null) {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        binding.containerLouder.gone()
                        movieAdapter.addMovies(it.data.results)

                    }
                    Resource.Status.ERROR -> {
                        Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                        binding.containerLouder.gone()
                    }

                    Resource.Status.LOADING ->
                        binding.containerLouder.visible()
                }
            }
        })
    }


}