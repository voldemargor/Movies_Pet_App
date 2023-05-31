package com.example.moviespetapp.presentation.moviedetails

import android.animation.ObjectAnimator
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviespetapp.databinding.FragmentMovieDetailsBinding
import com.example.moviespetapp.domain.Movie
import com.example.moviespetapp.domain.MovieShort
import com.example.moviespetapp.domain.Rating
import com.example.moviespetapp.domain.Votes
import com.example.moviespetapp.presentation.Utils
import com.example.moviespetapp.presentation.contract.HasBackIcon
import com.example.moviespetapp.presentation.contract.HasCustomTitle
import com.example.moviespetapp.presentation.contract.navigator
import com.example.moviespetapp.presentation.movieslist.MoviesListAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailsFragment : Fragment(), HasCustomTitle, HasBackIcon {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding: FragmentMovieDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentMovieDetailsBinding is null")

    private var movieId: Int? = null
    var movieName: String? = null
    private val viewModel by viewModels<MovieDetailsViewModel>()

    override fun setScreenTitle() = navigator().setScreenTitle(movieName.toString())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parseParams()
        viewModel.initMovie(movieId)
        observeViewModel()
        //setListeners()
    }

    override fun onResume() {
        super.onResume()
        setScreenTitle()
    }

    private fun parseParams() = arguments?.let {
        movieId = it.getInt(ARG_MOVIE_ID)
        movieName = it.getString(ARG_MOVIE_NAME)
    }

    private fun observeViewModel() {
        viewModel.currentMovie.observe(viewLifecycleOwner) {
            buildHeaderSection(it)
            buildTrailerSection(it)
            buildRatingSection(it.rating, it.votes)
            buildSimilarMoviesSection(it.similarMovies)
        }
    }

    private fun buildHeaderSection(movie: Movie) {
        with(binding) {
            if (movie.poster != null) {
                Utils.loadImage(movie.poster.url, ivPoster)
                Utils.loadPosterBackground(movie.poster.url, ivPosterBackground)
            }
            tvTitle.text = movie.name
            val ratingKp = Utils.getRatingRounded(movie.rating?.kp)
            tvRatingHeader.text = ratingKp
            tvRatingHeader.setTextColor(Utils.getRatingTextColor(requireContext(), ratingKp))
            tvVotesHeader.text = Utils.getVotesInKilos(movie.votes)
            tvEngTitle.text = movie.alternativeName
            tvYearAndGenres.text = Utils.getStringYearAndGenres(movie)
            tvCountryDurationAgeRating.text = Utils.getStringCountryDurationAgeRating(movie)
            iconBookmark.setOnClickListener { navigator().toast("В закладки") }
            tvDescription.text = movie.description

            if (tvDescription.lineCount > 6) {
                ivDescriptionCutoffGradient.visibility = View.VISIBLE
                tvDescription.setOnClickListener {
                    it as TextView
                    val animation = ObjectAnimator.ofInt(it, "maxLines", it.getLineCount())
                    animation.setDuration(200).start()
                    ivDescriptionCutoffGradient.visibility = View.GONE
                }
            }
        }
    }

    private fun buildTrailerSection(it: Movie) {
        val youtubeTrailer = Utils.getYoutubeTrailer(it.trailers)
        if (youtubeTrailer == null) {
            disableTrailerSection()
            return
        }

        Utils.loadImage(youtubeTrailer.previewUrl, binding.ivTrailer)

        binding.ivTrailer.setOnClickListener {
            val youtubeUri = Uri.parse(youtubeTrailer.videoUrl)
            startActivity(Intent(Intent.ACTION_VIEW, youtubeUri))
        }
    }

    private fun buildRatingSection(rating: Rating?, votes: Votes) {
        if (rating == null) {
            disableRatingSection()
            return
        }
        with(binding) {
            val ratingKp = Utils.getRatingRounded(rating.kp)
            tvRatingKp.text = ratingKp
            tvRatingKp.setTextColor(Utils.getRatingTextColor(requireContext(), ratingKp))
            tvRatingImdb.text = Utils.getRatingRounded(rating.imdb)

            tvRatingKpVotesCount.text = Utils.getVotesFormatted(votes.kp.toInt()) + " оценок"
            tvRatingImdbVotesCount.text = Utils.getVotesFormatted(votes.imdb.toInt()) + " оценок"
        }

    }

    private fun buildSimilarMoviesSection(similarMovies: List<MovieShort>?) {
        if (similarMovies.isNullOrEmpty()) {
            disableSimilarMoviesSection()
            return
        }
        val rvAdapter = SimilarMoviesListAdapter().apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
        binding.rvSimilarMovies.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvSimilarMovies.adapter = rvAdapter

        rvAdapter.onMovieClickListener = {
            navigator().displayMovieDetailsScreen(it.id, it.name)
        }

        rvAdapter.submitList(similarMovies)
    }

    private fun disableTrailerSection() {
        binding.sectionTrailer.visibility = View.GONE
    }

    private fun disableRatingSection() {
        binding.sectionRating.visibility = View.GONE
    }

    private fun disableSimilarMoviesSection() {
        binding.sectionSimilar.visibility = View.GONE
    }

    //private fun setListeners() {
    //}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        const val FRAGMENT_NAME = "movie_details_fragment"
        const val ARG_MOVIE_ID = "param_movie_id"
        const val ARG_MOVIE_NAME = "param_movie_name"

        fun newInstance(movieId: Int, movieName: String) = MovieDetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_MOVIE_ID, movieId)
                putString(ARG_MOVIE_NAME, movieName)
            }
        }
    }

}

class YoutubeTrailer(
    val previewUrl: String,
    val videoUrl: String
)


