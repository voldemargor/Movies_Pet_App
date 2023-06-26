package com.example.moviespetapp.presentation.mainscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.generateViewId
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.TextView
import androidx.constraintlayout.helper.widget.Flow
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviespetapp.Constants
import com.example.moviespetapp.R
import com.example.moviespetapp.databinding.FragmentMainBinding
import com.example.moviespetapp.domain.DataLoadingResult
import com.example.moviespetapp.domain.entity.Movie
import com.example.moviespetapp.presentation.contract.BottomNavItem
import com.example.moviespetapp.presentation.contract.HasCustomTitle
import com.example.moviespetapp.presentation.contract.navigator
import com.example.moviespetapp.presentation.movieslist.MoviesListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), HasCustomTitle, BottomNavItem {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding is null")

    private val viewModel by viewModels<MainFragmentViewModel>()
    private lateinit var adapters: MainFragmentAdapters

    private var isLaunchFirstTime = true

    override fun setScreenTitle() =
        navigator().setScreenTitle(resources.getString(R.string.title_main))

    override fun getBottomNavItemId(): Int = R.id.navItemFirstScreen

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null) isLaunchFirstTime = false

        viewModel.loadSectionsData()
        buildGenresSection()
        adapters = MainFragmentAdapters()
        initHorizontalListSection(adapters.New, binding.rvNew)
        initHorizontalListSection(adapters.Soon, binding.rvSoon)
        initHorizontalListSection(adapters.Popular, binding.rvPopular)
        initHorizontalListSection(adapters.Fiction, binding.rvFiction)
        initHorizontalListSection(adapters.Comedy, binding.rvComedy)
        initHorizontalListSection(adapters.Horror, binding.rvHorror)
        initHorizontalListSection(adapters.ForKids, binding.rvForKids)

        observeViewModel()
        //setListeners()
    }

    override fun onResume() {
        super.onResume()
        setScreenTitle()
        isLaunchFirstTime = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        viewModel.displayLoader.observe(viewLifecycleOwner) {
            if (it) binding.pbLoading.visibility = View.VISIBLE
            else binding.pbLoading.visibility = View.GONE
        }
        viewModel.newMovies.observe(viewLifecycleOwner) {
            if (it is DataLoadingResult.Success<*>)
                adapters.New.submitList(it.data as List<Movie>)
        }
        viewModel.soonMovies.observe(viewLifecycleOwner) {
            if (it is DataLoadingResult.Success<*>)
                adapters.Soon.submitList(it.data as List<Movie>)
        }
        viewModel.popularMovies.observe(viewLifecycleOwner) {
            if (it is DataLoadingResult.Success<*>)
                adapters.Popular.submitList(it.data as List<Movie>)
        }
        viewModel.fictionMovies.observe(viewLifecycleOwner) {
            if (it is DataLoadingResult.Success<*>)
                adapters.Fiction.submitList(it.data as List<Movie>)
        }
        viewModel.comedyMovies.observe(viewLifecycleOwner) {
            if (it is DataLoadingResult.Success<*>)
                adapters.Comedy.submitList(it.data as List<Movie>)
        }
        viewModel.horrorMovies.observe(viewLifecycleOwner) {
            if (it is DataLoadingResult.Success<*>)
                adapters.Horror.submitList(it.data as List<Movie>)
        }
        viewModel.kidMovies.observe(viewLifecycleOwner) {
            if (it is DataLoadingResult.Success<*>)
                adapters.ForKids.submitList(it.data as List<Movie>)
        }
    }

    //private fun setListeners() {
    //}

    private fun buildGenresSection() {
        viewModel.genres.observe(viewLifecycleOwner) {
            binding.layoutGenresGroup.removeAllViews()
            val flow = createFlowWidget()
            binding.layoutGenresGroup.addView(flow)

            for (genre in it) {
                val itemView =
                    layoutInflater.inflate(
                        R.layout.item_genre,
                        binding.layoutGenresGroup,
                        false)
                itemView.findViewById<TextView?>(R.id.tvGenreItem).text =
                    genre.name.replaceFirstChar { it.uppercase() }
                itemView.id = generateViewId()
                itemView.setOnClickListener {
                    navigator().displayMoviesListScreen(genreName = genre.name)
                }
                binding.layoutGenresGroup.addView(itemView)
                flow.addView(itemView)
            }
        }
    }

    private fun initHorizontalListSection(adapter: MoviesListAdapter, rv: RecyclerView) {
        rv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rv.adapter = adapter
        adapter.onMovieClickListener = {
            navigator().displayMovieDetailsScreen(it.id, it.name.toString())
        }
    }

    private fun createFlowWidget(): Flow {
        val flow = Flow(requireActivity()).apply {
            id = generateViewId()
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,
            )
            setWrapMode(Flow.WRAP_CHAIN)
            setHorizontalStyle(Flow.CHAIN_PACKED)
            setHorizontalGap(6)
            setVerticalGap(6)
            setHorizontalBias(0f)
            setOrientation(Flow.HORIZONTAL)
        }
        return flow
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}