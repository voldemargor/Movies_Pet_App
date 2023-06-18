package com.example.moviespetapp.presentation.moviedetails

import androidx.recyclerview.widget.DiffUtil
import com.example.moviespetapp.domain.entity.MovieShort

class SimilarMovieItemDiffCallback : DiffUtil.ItemCallback<MovieShort>() {

    override fun areItemsTheSame(oldItem: MovieShort, newItem: MovieShort): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieShort, newItem: MovieShort): Boolean {
        return oldItem.id == newItem.id
    }


}
