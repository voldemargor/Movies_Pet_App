package com.example.moviespetapp.presentation.bookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.moviespetapp.R
import com.example.moviespetapp.databinding.FragmentBookmarkBinding
import com.example.moviespetapp.presentation.contract.BottomNavItem
import com.example.moviespetapp.presentation.contract.HasCustomTitle
import com.example.moviespetapp.presentation.contract.navigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : Fragment(), HasCustomTitle, BottomNavItem {

    private var _binding: FragmentBookmarkBinding? = null
    private val binding: FragmentBookmarkBinding
        get() = _binding ?: throw RuntimeException("FragmentMineBinding is null")

    private val viewModel by viewModels<BookmarkViewModel>()

    override fun setScreenTitle() =
        navigator().setScreenTitle(resources.getString(R.string.title_bookmark))

    override fun getBottomNavItemId(): Int = R.id.navItemBookmark

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setScreenTitle()
        viewModel.initFavMoviesLD()
        observeViewModel()
        setListeners()
    }

    override fun onResume() {
        super.onResume()
        setScreenTitle()
    }

    private fun observeViewModel() {
        viewModel.movies.observe(viewLifecycleOwner) {
            binding.textView.text = "МОЕ \n\n ${it.get(0)}"
        }
    }

    private fun setListeners() {
        binding.textView.setOnClickListener() {
            navigator().displayMovieDetailsScreen(470185, "Обливион")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FRAGMENT_NAME = "first_fragment"

        fun newInstance() = BookmarkFragment()
    }

}