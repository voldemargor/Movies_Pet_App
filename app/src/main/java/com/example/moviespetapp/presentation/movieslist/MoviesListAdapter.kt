package com.example.moviespetapp.presentation.movieslist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.moviespetapp.Constants
import com.example.moviespetapp.databinding.ItemMovieBaseBinding
import com.example.moviespetapp.databinding.ItemMovieGridLayoutBinding
import com.example.moviespetapp.databinding.ItemMovieHorizontalLayoutBinding
import com.example.moviespetapp.domain.entity.Movie
import com.example.moviespetapp.presentation.Utils
import com.example.moviespetapp.presentation.Utils.Companion.loadImage
import com.example.moviespetapp.presentation.movieslist.MoviesListAdapter.ListType.*

class MoviesListAdapter(private val listType: ListType) :
    ListAdapter<Movie, MoviesListAdapter.ItemViewHolder>(MovieItemDiffCallback()) {

    var onMovieClickListener: ((Movie) -> Unit)? = null
    var onReachEndListener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        var binding: ViewBinding? = null

        when (listType) {
            HORIZONTAL -> binding = ItemMovieHorizontalLayoutBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

            GRID -> binding = ItemMovieGridLayoutBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        }

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val movie = getItem(position)

        val binding = when (listType) {
            HORIZONTAL -> (holder.binding as ItemMovieHorizontalLayoutBinding).baseLayout
            GRID -> (holder.binding as ItemMovieGridLayoutBinding).baseLayout
        }
        fillViewWithData(movie, binding)
        checkIfReachEnd(position)
    }

    private fun fillViewWithData(movie: Movie, binding: ItemMovieBaseBinding) {
        with(binding) {
            tvTitle.text = movie.name
            tvGenre.text = movie.genres?.get(0)?.name ?: ""

            val rating = movie.rating
            if (rating == null || movie.votes.kp == "0")
                tvRating.visibility = View.GONE
            else {
                val ratingKp = Utils.getRatingRounded(rating.kp)
                tvRating.text = ratingKp
                tvRating.setBackgroundResource(Utils.getRatingBgColor(ratingKp))
            }
            root.setOnClickListener { onMovieClickListener?.invoke(movie) }
            movie.poster?.let { ivPoster.loadImage(it.url) }
        }
    }

    private fun checkIfReachEnd(position: Int) {
        if (position == itemCount - Constants.ITEMS_BEFORE_CALL_REACH_END) {
            Log.d("mylog", "MoviesListAdapter: invoke onReachEndListener")
            onReachEndListener?.invoke()
        }
    }

    inner class ItemViewHolder(val binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    enum class ListType {
        HORIZONTAL,
        GRID,
    }

}


