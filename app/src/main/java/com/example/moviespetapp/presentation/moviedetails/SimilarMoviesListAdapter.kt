package com.example.moviespetapp.presentation.moviedetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviespetapp.databinding.ItemMovieBinding
import com.example.moviespetapp.databinding.ItemMovieHorizontalBinding
import com.example.moviespetapp.domain.MovieShort
import com.example.moviespetapp.presentation.Utils

class SimilarMoviesListAdapter :
    ListAdapter<MovieShort, SimilarMoviesListAdapter.ItemViewHolder>(SimilarMovieItemDiffCallback()) {

    var onMovieClickListener: ((MovieShort) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemMovieHorizontalBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val movie = getItem(position)
        with(holder.binding) {
            tvTitle.text = movie.name
            root.setOnClickListener { onMovieClickListener?.invoke(movie) }
            if (movie.poster != null)
                Utils.loadImage(movie.poster.url, ivPoster)
        }
    }

    inner class ItemViewHolder(val binding: ItemMovieHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

}