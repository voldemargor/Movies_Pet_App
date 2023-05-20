package com.example.moviespetapp.presentation.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.moviespetapp.R
import com.example.moviespetapp.databinding.FragmentMovieDetailsBinding
import com.example.moviespetapp.presentation.contract.HasBackIcon
import com.example.moviespetapp.presentation.contract.HasCustomTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(), HasCustomTitle, HasBackIcon {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding: FragmentMovieDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentMovieDetailsBinding is null")

    private var movieId: Int? = null
    var movieName: String? = null
    private val viewModel by viewModels<MovieDetailsViewModel>()

    override fun getScreenTitleRes(): Int = R.string.title_movie

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
        setListeners()
    }

    private fun parseParams() = arguments?.let {
        movieId = it.getInt(ARG_MOVIE_ID)
        movieName = it.getString(ARG_MOVIE_NAME)
    }

    private fun observeViewModel() {
        viewModel.currentMovie.observe(viewLifecycleOwner) {
            binding.textView.text = "ЭКРАН ФИЛЬМА \n\n $it"
        }
    }

    private fun setListeners() {
    }

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