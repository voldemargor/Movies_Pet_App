package com.example.moviespetapp.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView.OnScrollListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviespetapp.R
import com.example.moviespetapp.databinding.FragmentSearchBinding
import com.example.moviespetapp.presentation.Loading
import com.example.moviespetapp.presentation.Canceled
import com.example.moviespetapp.presentation.Error
import com.example.moviespetapp.presentation.Result
import com.example.moviespetapp.presentation.Utils.Companion.hideKeyboard
import com.example.moviespetapp.presentation.Utils.Companion.showKeyboard
import com.example.moviespetapp.presentation.contract.BottomNavItem
import com.example.moviespetapp.presentation.contract.HasCustomTitle
import com.example.moviespetapp.presentation.contract.navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(), HasCustomTitle, BottomNavItem {

    private lateinit var rvAdapter: SearchResultsAdapter

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding ?: throw RuntimeException("FragmentSearchBinding is null")

    private val viewModel by viewModels<SearchViewModel>()

    override fun setScreenTitle() =
        navigator().setScreenTitle(resources.getString(R.string.title_search))

    override fun getBottomNavItemId(): Int = R.id.navItemSearch

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
        setListeners()
        binding.etSearch.requestFocus()
        binding.etSearch.showKeyboard()
    }

    override fun onResume() {
        super.onResume()
        setScreenTitle()
    }

    private fun setupRecyclerView() {
        rvAdapter = SearchResultsAdapter().apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
        binding.rvMovies.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvMovies.adapter = rvAdapter
    }

    private fun observeViewModel() {
        viewModel.jobStatus.observe(viewLifecycleOwner) {
            binding.pbLoading.visibility = View.GONE
            binding.layoutSearchEmptyState.root.visibility = View.GONE
            when (it) {
                is Loading -> binding.pbLoading.visibility = View.VISIBLE

                is Canceled -> binding.pbLoading.visibility = View.GONE

                is Error -> navigator().toast("Loading Error: ${it.message}")

                is Result -> {
                    binding.layoutSearchEmptyState.root.visibility = View.GONE
                    rvAdapter.submitList(it.movies)
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(100)
                        binding.rvMovies.layoutManager?.scrollToPosition(0)
                    }
                }
            }
        }
        viewModel.showEmptyState.observe(viewLifecycleOwner) {
            binding.layoutSearchEmptyState.root.visibility = View.VISIBLE
        }
    }

    private fun setListeners() {
        binding.etSearch.doOnTextChanged { text, start, before, count ->
            if (count >= 2) {
                val input = text?.toString()
                if (!input.isNullOrEmpty())
                    viewModel.loadMovies(input)
            }
        }
        binding.rvMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            // Убрать клаву чтобы не закрывала результаты
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                binding.etSearch.hideKeyboard()
            }
        })
        rvAdapter.onMovieClickListener = {
            navigator().displayMovieDetailsScreen(it.id, it.name.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = SearchFragment()
    }

}