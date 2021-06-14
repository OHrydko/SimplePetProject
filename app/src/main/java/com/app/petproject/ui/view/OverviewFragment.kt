package com.app.petproject.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.app.petproject.BuildConfig
import com.app.petproject.databinding.FragmentOverviewBinding
import com.app.petproject.entiti.Resource
import com.app.petproject.entiti.info.Overview
import com.app.petproject.ui.viewmodel.OverviewViewModel
import com.app.petproject.utils.gone
import com.app.petproject.utils.visible
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class OverviewFragment : Fragment() {

    //id of film
    private var filmId = 0

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        //get id film from MainFragment
        if (bundle != null) {
            filmId = bundle.getInt("id")
        }
        //get data of film by id from MainFragment
        viewModel.getOverview(filmId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.response.collect { response ->

                when (response.status) {
                    Resource.Status.SUCCESS -> {
                        binding.containerLouder.gone()
                        response.data?.let { it -> loadDataToView(it) }
                    }

                    Resource.Status.ERROR -> {
                        Toast.makeText(activity, response.message, Toast.LENGTH_SHORT).show()
                        binding.overviewText.gone()
                        binding.containerLouder.gone()
                    }

                    Resource.Status.LOADING ->
                        binding.containerLouder.visible()
                }
            }
        }

        //back button
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    /**
     * Display data
     */
    private fun loadDataToView(overview: Overview) {
        val url = BuildConfig.API_PHOTO + overview.poster_path
        Glide.with(this).load(url).centerCrop().into(binding.image)

        binding.title.text = overview.original_title
        binding.date.text = overview.release_date

        binding.overview.text = overview.overview
        val genre: List<String> = overview.genres.map { it.name }
        val director: List<String> = overview.production_companies.map { it.name }
        binding.genres.text = genre.joinToString(separator = ", ")
        binding.directors.text = director.joinToString(separator = ", ")
    }


}