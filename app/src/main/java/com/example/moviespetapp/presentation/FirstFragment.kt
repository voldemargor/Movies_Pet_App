package com.example.moviespetapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.moviespetapp.R
import com.example.moviespetapp.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding: FragmentFirstBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding is null")

    private val viewModel by viewModels<FirstViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initMoviesLD()
        observeViewModel()
        setListeners()
    }

    private fun observeViewModel() {
        viewModel.movies.observe(viewLifecycleOwner) {
            binding.textView.text = "ГЛАВНОЕ \n\n ${it.get(0)}"
        }
    }

    private fun setListeners() {
        binding.textView.setOnClickListener() {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MovieDetailsFragment.newInstance(2))
                .addToBackStack(MovieDetailsFragment.FRAGMENT_NAME)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FRAGMENT_NAME = "first_fragment"

        fun newInstance() = FirstFragment()
    }

}