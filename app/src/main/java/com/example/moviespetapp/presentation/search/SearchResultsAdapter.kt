package com.example.moviespetapp.presentation.search

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
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
    var onReachEndListener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemSearchBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val movie = getItem(position)

        with(holder.binding) {
            tvTitle.text = movie.name
            altName.text = movie.alternativeName ?: ""

            if (movie.rating == null)
                tvRating.visibility = View.GONE
            else {
                val rating = Utils.getRatingRounded(movie.rating.kp)
                tvRating.text = rating
                tvRating.setTextColor(Utils.getRatingTextColor(root.context, rating))
            }
            root.setOnClickListener { onMovieClickListener?.invoke(movie) }
            movie.poster?.let { ivPoster.loadImage(it.url) }
        }

        checkIfReachEnd(position)
    }

    private fun checkIfReachEnd(position: Int) {
        if (position == itemCount - Constants.ITEMS_BEFORE_CALL_REACH_END) {
            Log.d("mylog", "MoviesListAdapter: invoke onReachEndListener")
            onReachEndListener?.invoke()
        }
    }

    inner class ItemViewHolder(val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

}


