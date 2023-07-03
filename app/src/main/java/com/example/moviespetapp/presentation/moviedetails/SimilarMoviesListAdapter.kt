package com.example.moviespetapp.presentation.moviedetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviespetapp.databinding.ItemMovieSimilarBinding
import com.example.moviespetapp.domain.entity.MovieSimilar
import com.example.moviespetapp.presentation.Utils.Companion.loadImage

class SimilarMoviesListAdapter :
    ListAdapter<MovieSimilar, SimilarMoviesListAdapter.ItemViewHolder>(SimilarMovieItemDiffCallback()) {

    var onMovieClickListener: ((MovieSimilar) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemMovieSimilarBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val movie = getItem(position)
        with(holder.binding) {
            tvTitle.text = movie.name
            root.setOnClickListener { onMovieClickListener?.invoke(movie) }
            movie.poster?.let { ivPoster.loadImage(it.url) }
        }
    }

    inner class ItemViewHolder(val binding: ItemMovieSimilarBinding) :
        RecyclerView.ViewHolder(binding.root)

}