package com.app.petproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.petproject.R
import com.app.petproject.entiti.Results
import com.bumptech.glide.Glide
import java.util.*


class MovieAdapter(
    private val movies: ArrayList<Results>,
    private val onItemClick: OnItemClick
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    interface OnItemClick {
        fun onClick(id: Int)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {
        val movie: Results = movies[position]
        holder.name?.text = movie.original_title
        val url = "https://image.tmdb.org/t/p/w500" + movie.poster_path
        Glide.with(holder.itemView).load(url).into(holder.image)

        holder.description?.text = movie.overview
        holder.container?.setOnClickListener { onItemClick.onClick(movie.id) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun addMovies(movie: ArrayList<Results>?) {
        movie?.let { movies.addAll(it) }
        notifyDataSetChanged()
    }


    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.image)
        var name: TextView? = itemView.findViewById(R.id.name)
        var description: TextView? = itemView.findViewById(R.id.description)
        var container: ConstraintLayout? = itemView.findViewById(R.id.container)

    }


}