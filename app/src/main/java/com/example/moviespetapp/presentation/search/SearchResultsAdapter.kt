package com.example.moviespetapp.presentation.search

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviespetapp.Constants
import com.example.moviespetapp.databinding.ItemSearchBinding
import com.example.moviespetapp.domain.entity.Movie
import com.example.moviespetapp.presentation.Utils
import com.example.moviespetapp.presentation.Utils.Companion.loadImage
import com.example.moviespetapp.presentation.movieslist.MovieItemDiffCallback

class SearchResultsAdapter() :
    ListAdapter<Movie, SearchResultsAdapter.ItemViewHolder>(MovieItemDiffCallback()) {

    var onMovieClickListener: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemSearchBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val movie = getItem(position)
        handleTitles(movie, holder.binding)
        handleRating(movie, holder.binding)
        with(holder.binding) {
            root.setOnClickListener { onMovieClickListener?.invoke(movie) }
            movie.poster?.let { ivPoster.loadImage(it.url) }
        }
    }

    private fun handleTitles(movie: Movie, binding: ItemSearchBinding) {
        with(binding) {
            if (movie.name == "") {
                tvTitle.text = movie.alternativeName
                altName.text = movie.year.toString()
            } else {
                tvTitle.text = movie.name
                if (movie.alternativeName.isNullOrBlank()) altName.text = movie.year.toString()
                else altName.text = movie.alternativeName + ", ${movie.year}"
            }
        }
    }

    private fun handleRating(movie: Movie, binding: ItemSearchBinding) {
        with(binding) {
            if (movie.rating == null)
                tvRating.visibility = View.GONE
            else {
                val rating = Utils.getRatingRounded(movie.rating.kp)
                tvRating.text = rating
                tvRating.setTextColor(Utils.getRatingTextColor(root.context, rating))
            }
        }
    }

    inner class ItemViewHolder(val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

}


