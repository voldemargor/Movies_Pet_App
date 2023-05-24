package com.example.moviespetapp.presentation.movieslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviespetapp.R
import com.example.moviespetapp.databinding.FragmentMoviesListScreenBinding
import com.example.moviespetapp.presentation.MoviesLoading
import com.example.moviespetapp.presentation.MoviesLoadingError
import com.example.moviespetapp.presentation.MoviesLoadingResult
import com.example.moviespetapp.presentation.contract.HasBackIcon
import com.example.moviespetapp.presentation.contract.HasCustomTitle
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
        viewModel.loadMovies(genreName)
        setupRecyclerView()
        observeViewModel()
        setListeners()
    }

    private fun parseParams() = arguments?.let {
        genreName =
            it.getString(ARG_GENRE_NAME) ?: throw RuntimeException("Param genreName is NULL")
    }

    private fun setupRecyclerView() {
        rvAdapter = MoviesListAdapter().apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
        binding.rvMovies.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvMovies.adapter = rvAdapter

        //binding.rvMovies.addItemDecoration(
        //    MoviesListItemDecoration(3, 50, false))

        rvAdapter.onMovieClickListener = {
            navigator().displayMovieDetailsScreen(it.id, it.name.toString())
        }
    }

    private fun observeViewModel() {
        viewModel.movies.observe(viewLifecycleOwner) {
            binding.pbLoading.visibility = View.GONE
            when (it) {
                is MoviesLoading -> binding.pbLoading.visibility = View.VISIBLE

                is MoviesLoadingError -> navigator().showToast("Loading Error: ${it.message}")

                is MoviesLoadingResult -> rvAdapter.submitList(it.movies)

            }
        }

    }

    private fun setListeners() {

        //binding.rvMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        //    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        //        super.onScrollStateChanged(recyclerView, newState)
        //        if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
        //            navigator().showToast("КОНЕЦ")
        //            viewModel.loadMovies(genreName)
        //        }
        //    }
        //})

        rvAdapter.onReachEndListener = {
            //navigator().showToast("КОНЕЦ")
            //viewModel.loadMovies(genreName)
        }

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