package com.example.moviespetapp.presentation.moviedetails

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviespetapp.R
import com.example.moviespetapp.databinding.FragmentMovieDetailsBinding
import com.example.moviespetapp.domain.entity.Movie
import com.example.moviespetapp.domain.entity.MovieSimilar
import com.example.moviespetapp.domain.entity.Rating
import com.example.moviespetapp.domain.entity.Votes
import com.example.moviespetapp.presentation.MovieDetails
import com.example.moviespetapp.presentation.Utils
import com.example.moviespetapp.presentation.Utils.Companion.loadBlurredImage
import com.example.moviespetapp.presentation.Utils.Companion.loadImage
import com.example.moviespetapp.presentation.contract.HasBackIcon
import com.example.moviespetapp.presentation.contract.HasCustomTitle
import com.example.moviespetapp.presentation.contract.navigator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailsFragment : Fragment(), HasCustomTitle, HasBackIcon {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding: FragmentMovieDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentMovieDetailsBinding is null")

    private var movieId: Int? = null
    var movieName: String? = null
    private val viewModel by viewModels<MovieDetailsViewModel>()
    private lateinit var movie: Movie

    override fun setScreenTitle() = navigator().setScreenTitle(movieName.toString())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parseParams()
        viewModel.loadMovieData(movieId)
        observeViewModel()
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
            movie = it
            buildHeaderSection()
            buildTrailerSection()
            buildRatingSection(it.rating, it.votes)
            buildSimilarMoviesSection(it.similarMovies)
        }
        viewModel.isBookmark.observe(viewLifecycleOwner) {
            setBookmarkIconColor(it)
        }
        viewModel.displayLoader.observe(viewLifecycleOwner) {
            if (it) displayLoading()
            else hideLoading()
        }
        viewModel.hasException.observe(viewLifecycleOwner) {
            navigator().showExceptionDialog(it)
        }
    }

    private fun buildHeaderSection() {
        with(binding) {
            movie.poster?.let {
                ivPoster.loadImage(it.url)
                ivPosterBackground.loadBlurredImage(it.url)
            }
            tvTitle.text = movie.name

            handleRating()

            if (movie.votes.kp.toInt() < 999) tvVotesHeader.visibility = View.GONE
            else tvVotesHeader.text = Utils.getVotesInKilos(movie.votes)

            if (movie.alternativeName.isNullOrEmpty()) tvEngTitle.visibility = View.GONE
            else tvEngTitle.text = movie.alternativeName

            tvYearAndGenres.text = Utils.getStringYearAndGenres(movie)
            tvCountryDurationAgeRating.text = Utils.getStringCountryDurationAgeRating(movie)

            iconBookmark.setOnClickListener {
                viewModel.handleBookmarkAction()
            }

            if (movie.shortDescription == null) tvDescription.text = movie.description
            else tvDescription.text = movie.shortDescription + "\n\n" + movie.description

            handleDescriptionCutoff()
        }
    }

    private fun handleRating() {
        val rating = movie.rating
        with(binding) {
            if (shouldHideRating())
                tvRatingHeader.visibility = View.GONE
            else {
                val ratingKp = Utils.getRatingRounded(rating?.kp)
                tvRatingHeader.text = ratingKp
                tvRatingHeader.setTextColor(
                    Utils.getRatingHeaderSectionTextColor(
                        requireContext(),
                        ratingKp
                    )
                )
            }
        }
    }

    private fun handleDescriptionCutoff() {
        with(binding) {
            if (shouldHideRating()) {
                val animation =
                    ObjectAnimator.ofInt(tvDescription, "maxLines", tvDescription.getLineCount())
                animation.setDuration(0).start()
            } else {
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
    }

    private fun buildTrailerSection() {
        val youtubeTrailer = Utils.getYoutubeTrailer(movie.trailers)
        if (youtubeTrailer == null) {
            disableTrailerSection()
            return
        }

        binding.ivTrailer.loadImage(youtubeTrailer.previewUrl)

        binding.ivTrailer.setOnClickListener {
            val youtubeUri = Uri.parse(youtubeTrailer.videoUrl)
            startActivity(Intent(Intent.ACTION_VIEW, youtubeUri))
        }
    }

    private fun buildRatingSection(rating: Rating?, votes: Votes) {
        if (rating == null || votes.kp == "0") {
            disableRatingSection()
            return
        }
        with(binding) {
            val ratingKp = Utils.getRatingRounded(rating.kp)
            tvRatingKp.text = ratingKp
            tvRatingKp.setTextColor(Utils.getRatingTextColor(requireContext(), ratingKp))
            var ratingImdb = Utils.getRatingRounded(rating.imdb)
            if (ratingImdb == "0.0") ratingImdb = "-"
            tvRatingImdb.text = ratingImdb
            tvRatingImdb.setTextColor(resources.getColor(R.color.text_rating_neutral, null))

            tvRatingKpVotesCount.text = Utils.getVotesFormatted(votes.kp.toInt()) + " оценок"
            tvRatingImdbVotesCount.text = Utils.getVotesFormatted(votes.imdb.toInt()) + " оценок"
        }

    }

    private fun buildSimilarMoviesSection(similarMovies: List<MovieSimilar>?) {
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
            navigator().displayScreen(MovieDetails(it.id, it.name))
        }

        rvAdapter.submitList(similarMovies)
    }

    private fun shouldHideRating(): Boolean {
        val rating = movie.rating ?: return true
        if (Utils.getRatingRounded(rating.kp) == "0.0") return true
        return movie.votes.kp == "0"
    }

    private fun setBookmarkIconColor(it: Boolean) {
        if (it) DrawableCompat.setTint(binding.iconBookmarkDrawable.drawable, Color.RED)
        else DrawableCompat.setTint(
            binding.iconBookmarkDrawable.drawable,
            resources.getColor(R.color.bottom_nav_unchecked, null)
        )
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

    private fun displayLoading() {
        binding.layoutLoadingMovieDetails.root.visibility = View.VISIBLE
        binding.layoutLoadingMovieDetails.layoutToAnimate.startAnimation(
            AlphaAnimation(0.6f, 1f).apply {
                duration = 600
                repeatMode = Animation.REVERSE
                //interpolator = LinearInterpolator()
                repeatCount = Animation.INFINITE
            })
    }

    private fun hideLoading() {
        binding.layoutLoadingMovieDetails.layoutToAnimate.clearAnimation()
        binding.layoutLoadingMovieDetails.root.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
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


