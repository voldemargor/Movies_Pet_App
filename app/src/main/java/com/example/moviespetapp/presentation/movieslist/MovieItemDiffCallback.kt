package com.example.moviespetapp.presentation.movieslist

import androidx.recyclerview.widget.DiffUtil
import com.example.moviespetapp.domain.entity.Movie

class MovieItemDiffCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

}
