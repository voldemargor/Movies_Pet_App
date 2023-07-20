package com.example.moviespetapp.presentation.moviedetails

import androidx.recyclerview.widget.DiffUtil
import com.example.moviespetapp.domain.entity.MovieSimilar

class SimilarMovieItemDiffCallback : DiffUtil.ItemCallback<MovieSimilar>() {

    override fun areItemsTheSame(oldItem: MovieSimilar, newItem: MovieSimilar): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieSimilar, newItem: MovieSimilar): Boolean {
        return oldItem.id == newItem.id
    }

}
