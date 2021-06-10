package com.app.petproject.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.petproject.BuildConfig
import com.app.petproject.databinding.MainItemBinding
import com.app.petproject.entiti.Results
import com.bumptech.glide.Glide

class MoviePageAdapter :
    PagingDataAdapter<Results, MoviePageAdapter.TestViewHolder>(REPO_COMPARATOR) {
    var onItemClick: ((Int) -> Unit)? = null

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TestViewHolder {
        return TestViewHolder(
            MainItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Results>() {
            override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean =
                oldItem == newItem
        }
    }

    inner class TestViewHolder(private val binding: MainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Results) = with(binding) {

            name.text = item.original_title
            val url = BuildConfig.API_PHOTO + item.poster_path
            Glide.with(itemView).load(url).into(image)

            description.text = item.overview
            container.setOnClickListener { onItemClick?.invoke(item.id) }
        }
    }
}