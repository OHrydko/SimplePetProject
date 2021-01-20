package com.app.petproject.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.app.petproject.R
import com.app.petproject.entiti.information.Overview
import com.app.petproject.ui.viewmodel.IOverviewFragmentViewModel
import com.bumptech.glide.Glide
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class OverviewFragment : Fragment() {

    @Inject
    lateinit var viewModel: IOverviewFragmentViewModel
    var ids = 0
    lateinit var image: ImageButton
    lateinit var title: TextView
    lateinit var overviewText: TextView
    lateinit var date: TextView
    lateinit var directors: TextView
    lateinit var genres: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            ids = bundle.getInt("id")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)
        val view = inflater.inflate(R.layout.fragment_overview, container, false)
        image = view.findViewById(R.id.image)
        title = view.findViewById(R.id.title)
        overviewText = view.findViewById(R.id.overview)
        date = view.findViewById(R.id.date)
        directors = view.findViewById(R.id.directors)
        genres = view.findViewById(R.id.genres)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getOverview(ids)
        viewModel.movie.observe(requireActivity(), Observer {
            if (it.data != null)
                loadDataToView(it.data)
        })
    }

    private fun loadDataToView(overview: Overview) {
        val url = "https://image.tmdb.org/t/p/w500" + overview.poster_path
        Glide.with(this).load(url).centerCrop().into(image)

        title.text = overview.original_title
        date.text = overview.release_date

        overviewText.text = overview.overview
        val genre: List<String> = overview.genres.map { it.name }
        val director: List<String> = overview.production_companies.map { it.name }
        genres.text = genre.joinToString(separator = ", ")
        directors.text = director.joinToString(separator = ", ")
    }


}