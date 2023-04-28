package com.example.moviespetapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviespetapp.databinding.ActivityMainBinding
import com.example.moviespetapp.domain.GetMoviesUseCase
import com.example.moviespetapp.domain.Repository
import com.example.moviespetapp.presentation.BookmarkFragment
import com.example.moviespetapp.presentation.FirstFragment
import com.example.moviespetapp.presentation.SearchFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject lateinit var repository: Repository
    @Inject lateinit var getMoviesUseCase: GetMoviesUseCase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        //setContentView(R.layout.activity_main)

        binding.bottomNav.selectedItemId = R.id.itemFirstScreen
        binding.bottomNav.setOnItemSelectedListener() {
            when (it.itemId) {
                R.id.itemFirstScreen -> {
                    launchFirstScreen()
                }

                R.id.itemBookmark -> {
                    launchBookmarks()
                }

                R.id.itemSearch -> {
                    launchSearch()
                }
            }
            true
        }

        //Log.d("mylog", "MainActivity repository: $repository")
        //Log.d("mylog", "MainActivity getMoviesUseCase: $getMoviesUseCase")

        //val movies = repository.getMovies()
        //
        //lifecycleScope.launch {
        //    delay(3_000)
        //    Log.d("mylog", repository.getMovie(1).toString())
        //}

        //for (movie in movies.value!!) {
        //    Log.d("mylog", movie.name)
        //    Log.d("mylog", movie.year.toString())
        //    Log.d("mylog", movie.description)
        //}

    }

    private fun launchFirstScreen() = supportFragmentManager.beginTransaction()
        .setCustomAnimations(androidx.appcompat.R.anim.abc_fade_in, androidx.appcompat.R.anim.abc_fade_out)
        .replace(R.id.fragment_container, FirstFragment.newInstance())
        .addToBackStack(FirstFragment.FRAGMENT_NAME)
        .commit()


    private fun launchBookmarks() = supportFragmentManager.beginTransaction()
        .setCustomAnimations(androidx.appcompat.R.anim.abc_fade_in, androidx.appcompat.R.anim.abc_fade_out)
        .replace(R.id.fragment_container, BookmarkFragment.newInstance())
        .addToBackStack(FirstFragment.FRAGMENT_NAME)
        .commit()

    private fun launchSearch() = supportFragmentManager.beginTransaction()
        .setCustomAnimations(androidx.appcompat.R.anim.abc_fade_in, androidx.appcompat.R.anim.abc_fade_out)
        .replace(R.id.fragment_container, SearchFragment.newInstance())
        .addToBackStack(FirstFragment.FRAGMENT_NAME)
        .commit()


}