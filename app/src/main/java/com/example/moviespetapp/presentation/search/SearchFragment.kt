package com.example.moviespetapp.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.moviespetapp.R
import com.example.moviespetapp.databinding.FragmentSearchBinding
import com.example.moviespetapp.presentation.contract.BottomNavItem
import com.example.moviespetapp.presentation.contract.HasCustomTitle
import com.example.moviespetapp.presentation.contract.navigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), HasCustomTitle, BottomNavItem {

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
        //setScreenTitle()
        viewModel.initLD()
        observeViewModel()
        setListeners()
    }

    override fun onResume() {
        super.onResume()
        setScreenTitle()
    }

    private fun observeViewModel() {
    }

    private fun setListeners() {
        //binding.textView.setOnClickListener() {
        //    requireActivity().supportFragmentManager.beginTransaction()
        //        .replace(R.id.fragment_container, MovieDetailsFragment.newInstance(2))
        //        .addToBackStack(MovieDetailsFragment.FRAGMENT_NAME)
        //        .commit()
        //}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FRAGMENT_NAME = "search_fragment"

        fun newInstance() = SearchFragment()
    }

}