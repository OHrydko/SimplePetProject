package com.app.petproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.petproject.BuildConfig
import com.app.petproject.databinding.MainItemBinding
import com.app.petproject.entiti.Results
import com.bumptech.glide.Glide
import java.util.*


class MovieAdapter(
    private val movies: ArrayList<Results>,
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var onItemClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        return MovieViewHolder(
            MainItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {
        val movie: Results = movies[position]
        holder.binding.name.text = movie.original_title
        val url = BuildConfig.API_PHOTO + movie.poster_path
        Glide.with(holder.itemView).load(url).into(holder.binding.image)

        holder.binding.description.text = movie.overview
        holder.binding.container.setOnClickListener { onItemClick?.invoke(movie.id) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun addMovies(movie: ArrayList<Results>?) {
        movie?.let { movies.addAll(it) }
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(val binding: MainItemBinding) :
        RecyclerView.ViewHolder(binding.root)


}