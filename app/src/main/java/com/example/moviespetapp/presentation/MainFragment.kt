package com.example.moviespetapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.generateViewId
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.helper.widget.Flow
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.moviespetapp.R
import com.example.moviespetapp.databinding.FragmentMainBinding
import com.example.moviespetapp.presentation.contract.BottomNavItem
import com.example.moviespetapp.presentation.contract.HasCustomTitle
import com.example.moviespetapp.presentation.contract.navigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), HasCustomTitle, BottomNavItem {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding is null")

    private val viewModel by viewModels<MainFragmentViewModel>()

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
        //setScreenTitle()
        viewModel.initMoviesLD()
        viewModel.initGenres()
        observeViewModel()
        setListeners()
        generateGenresSection()
    }

    override fun onResume() {
        super.onResume()
        setScreenTitle()
    }

    private fun observeViewModel() {
        //viewModel.movies.observe(viewLifecycleOwner) {
        //    binding.textView.text = "ГЛАВНОЕ\n\n${it.get(0)}"
        //}
    }

    private fun setListeners() {
        binding.textView.setOnClickListener() {
            navigator().displayMovieDetailsScreen(666, "Форсаж")
        }
    }

    private fun generateGenresSection() {
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
                itemView.setOnClickListener { navigator().displayMoviesListScreen(genre.name) }
                binding.layoutGenresGroup.addView(itemView)
                flow.addView(itemView)
            }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FRAGMENT_NAME = "main_fragment"

        fun newInstance() = MainFragment()
    }

}