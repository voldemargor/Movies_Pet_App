package com.example.moviespetapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.moviespetapp.R
import com.example.moviespetapp.databinding.FragmentMoviesListScreenBinding
import com.example.moviespetapp.presentation.contract.HasBackIcon
import com.example.moviespetapp.presentation.contract.HasCustomTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListScreenFragment : Fragment(), HasCustomTitle, HasBackIcon {

    private var _binding: FragmentMoviesListScreenBinding? = null
    private val binding: FragmentMoviesListScreenBinding
        get() = _binding ?: throw RuntimeException("FragmentMoviesListScreenBinding is null")

    private lateinit var genreName: String
    private val viewModel by viewModels<MoviesListScreenViewModel>()

    override fun getScreenTitleRes(): Int = R.string.title_movie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentMoviesListScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parseParams()
        viewModel.initMovies(genreName)
        observeViewModel()
        setListeners()
    }

    private fun parseParams() = arguments?.let {
        genreName =
            it.getString(ARG_GENRE_NAME) ?: throw RuntimeException("Param genreName is NULL")
    }

    private fun observeViewModel() {
        viewModel.movies.observe(viewLifecycleOwner) {
            var text: String = ""
            for (movie in it) {
                text += movie.name + "\n"
            }
            binding.textView.text = text
        }
    }

    private fun setListeners() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FRAGMENT_NAME = "movies_list_screen_fragment"
        const val ARG_GENRE_NAME = "genreName"

        fun newInstance(genreName: String) = MoviesListScreenFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_GENRE_NAME, genreName)
            }
        }
    }

}