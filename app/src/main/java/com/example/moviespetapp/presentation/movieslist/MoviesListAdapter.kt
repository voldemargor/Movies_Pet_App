package com.example.moviespetapp.presentation.movieslist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.moviespetapp.R
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
            tvRating.text = Utils.getRatingFromEntity(movie)
            tvRating.setBackgroundResource(Utils.getRatingBgColor(movie))
            root.setOnClickListener { onMovieClickListener?.invoke(movie) }
            Glide.with(root)
                .load(movie.poster?.url)
                //.placeholder(R.drawable.drawable)
                .error(R.drawable.ic_home)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivPoster)
        }
        checkIfReachEnd(position)
    }

    private fun checkIfReachEnd(position: Int) {
        if (position == itemCount - 6) {
            Log.d("mylog", "MoviesListAdapter: invoke onReachEndListener")
            onReachEndListener?.invoke()
        }
    }

    inner class ItemViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

}