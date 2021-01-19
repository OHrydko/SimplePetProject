package com.app.petproject.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.app.petproject.R
import com.app.petproject.ui.viewmodel.IMainFragmentViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    lateinit var viewModel: IMainFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.movie.observe(requireActivity(), Observer {
            if (it.data != null)
                Toast.makeText(
                    requireContext(),
                    it.data.results[0].backdrop_path,
                    Toast.LENGTH_LONG
                ).show()
        })
    }

}