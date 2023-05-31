package com.example.moviespetapp.presentation.movieslist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviespetapp.Constants
import com.example.moviespetapp.databinding.ItemMovieBinding
import com.example.moviespetapp.domain.Movie
import com.example.moviespetapp.presentation.Utils

class MoviesListAdapter :
    ListAdapter<Movie, MoviesListAdapter.ItemViewHolder>(MovieItemDiffCallback()) {

    var onMovieClickListener: ((Movie) -> Unit)? = null
    var onReachEndListener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemMovieBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val movie = getItem(position)
        with(holder.binding) {
            tvTitle.text = movie.name
            tvGenre.text = movie.genres?.get(0)?.name ?: ""
            val ratingKp = Utils.getRatingRounded(movie.rating?.kp)
            tvRating.text = ratingKp
            tvRating.setBackgroundResource(Utils.getRatingBgColor(ratingKp))
            root.setOnClickListener { onMovieClickListener?.invoke(movie) }
            if (movie.poster != null)
                Utils.loadImage(movie.poster.url, ivPoster)
        }
        checkIfReachEnd(position)
    }

    private fun checkIfReachEnd(position: Int) {
        if (position == itemCount - Constants.ITEMS_BEFORE_CALL_REACH_END) {
            Log.d("mylog", "MoviesListAdapter: invoke onReachEndListener")
            onReachEndListener?.invoke()
        }
    }

    inner class ItemViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

}