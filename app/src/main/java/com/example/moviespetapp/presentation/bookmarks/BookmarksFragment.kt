package com.example.moviespetapp.presentation.bookmarks

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
import com.example.moviespetapp.presentation.Canceled
import com.example.moviespetapp.presentation.Error
import com.example.moviespetapp.presentation.Loading
import com.example.moviespetapp.presentation.Result
import com.example.moviespetapp.presentation.contract.BottomNavItem
import com.example.moviespetapp.presentation.contract.HasCustomTitle
import com.example.moviespetapp.presentation.contract.MovieDetails
import com.example.moviespetapp.presentation.contract.navigator
import com.example.moviespetapp.presentation.movieslist.MoviesListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarksFragment : Fragment(), HasCustomTitle, BottomNavItem {

    private lateinit var rvAdapter: MoviesListAdapter

    private var _binding: FragmentMoviesListScreenBinding? = null
    private val binding: FragmentMoviesListScreenBinding
        get() = _binding ?: throw RuntimeException("FragmentMoviesListScreenBinding is null")

    private val viewModel by viewModels<BookmarksViewModel>()

    override fun setScreenTitle() =
        navigator().setScreenTitle(resources.getString(R.string.title_bookmark))

    override fun getBottomNavItemId(): Int = R.id.navItemBookmark

    override fun handleDoubleBottomMenuClick() = binding.rvMovies.smoothScrollToPosition(0)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesListScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadMovies()
        setupRecyclerView()
        observeViewModel()
        setListeners()
    }

    override fun onResume() {
        super.onResume()
        setScreenTitle()
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
            binding.layoutBookmarksEmptyState.root.visibility = View.GONE
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
        viewModel.showEmptyState.observe(viewLifecycleOwner) {
            binding.layoutBookmarksEmptyState.root.visibility = View.VISIBLE
        }
        viewModel.hasException.observe(viewLifecycleOwner) {
            navigator().displayExceptionDialog(it)
        }
    }

    private fun setListeners() {
        rvAdapter.onMovieClickListener = {
            navigator().displayScreen(MovieDetails(it.id, it.name!!))
        }
        rvAdapter.onReachEndListener = { viewModel.loadMovies() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.reset()
    }

    companion object {
        fun newInstance() = BookmarksFragment()
    }

}