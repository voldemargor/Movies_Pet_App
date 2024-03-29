package com.example.moviespetapp.presentation.movieslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviespetapp.databinding.FragmentMoviesListScreenBinding
import com.example.moviespetapp.presentation.Canceled
import com.example.moviespetapp.presentation.Error
import com.example.moviespetapp.presentation.Loading
import com.example.moviespetapp.presentation.Result
import com.example.moviespetapp.presentation.contract.HasBackIcon
import com.example.moviespetapp.presentation.contract.HasCustomTitle
import com.example.moviespetapp.presentation.contract.MovieDetails
import com.example.moviespetapp.presentation.contract.navigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListScreenFragment : Fragment(), HasCustomTitle, HasBackIcon {

    private lateinit var rvAdapter: MoviesListAdapter

    private var _binding: FragmentMoviesListScreenBinding? = null
    private val binding: FragmentMoviesListScreenBinding
        get() = _binding ?: throw RuntimeException("FragmentMoviesListScreenBinding is null")

    private lateinit var genreName: String
    private val viewModel by viewModels<MoviesListScreenViewModel>()

    override fun setScreenTitle() =
        navigator().setScreenTitle(genreName.replaceFirstChar { it.uppercase() })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesListScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parseParams()
        viewModel.loadMovies(genreName)
        setupRecyclerView()
        observeViewModel()
        setListeners()
    }

    override fun onResume() {
        super.onResume()
        setScreenTitle()
    }

    private fun parseParams() = arguments?.let {
        genreName =
            it.getString(ARG_GENRE_NAME) ?: throw RuntimeException("Param genreName is NULL")
    }

    private fun setupRecyclerView() {
        rvAdapter = MoviesListAdapter(MoviesListAdapter.ListType.GRID).apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
        binding.rvMovies.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvMovies.adapter = rvAdapter
    }

    private fun observeViewModel() {
        viewModel.jobStatus.observe(viewLifecycleOwner) {
            binding.pbLoading.visibility = View.GONE
            when (it) {
                is Loading -> binding.pbLoading.visibility = View.VISIBLE

                is Canceled -> binding.pbLoading.visibility = View.GONE

                is Error -> navigator().toast("Loading Error: ${it.message}")

                is Result -> {
                    rvAdapter.submitList(it.movies)
                }
            }
        }
        viewModel.hasException.observe(viewLifecycleOwner) {
            navigator().displayExceptionDialog(it)
        }
    }

    private fun setListeners() {
        rvAdapter.onMovieClickListener = {
            navigator().displayScreen(MovieDetails(it.id, it.name!!))
        }
        rvAdapter.onReachEndListener = { viewModel.loadMovies(genreName) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_GENRE_NAME = "genreName"

        fun newInstance(genreName: String) = MoviesListScreenFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_GENRE_NAME, genreName)
            }
        }
    }

}