package com.app.petproject.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.petproject.R
import com.app.petproject.adapter.MovieAdapter
import com.app.petproject.entiti.Resource
import com.app.petproject.ui.viewmodel.IMainFragmentViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MainFragment : Fragment(), MovieAdapter.OnItemClick {

    @Inject
    lateinit var viewModel: IMainFragmentViewModel
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.containerLouder)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        movieAdapter = MovieAdapter(ArrayList(), this)
        val layoutManagerRV = LinearLayoutManager(requireContext())
        recyclerView.apply {
            layoutManager = layoutManagerRV
            setHasFixedSize(true)
            adapter = movieAdapter
        }
        viewModel.movie.observe(requireActivity(), Observer {
            if (it.data != null) {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        movieAdapter.addMovies(it.data.results)

                    }
                    Resource.Status.ERROR ->
                        Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                    Resource.Status.LOADING ->
                        progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onClick(id: Int) {
        val fragment = OverviewFragment()
        val bundle = Bundle()
        bundle.putInt("id", id)
        fragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.frame, fragment)
            .addToBackStack(null)
            .commit()
    }

}