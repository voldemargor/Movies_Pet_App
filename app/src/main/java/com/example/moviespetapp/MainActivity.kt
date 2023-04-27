package com.example.moviespetapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviespetapp.domain.GetMoviesUseCase
import com.example.moviespetapp.domain.Repository
import com.example.moviespetapp.presentation.FirstFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var repository: Repository
    @Inject lateinit var getMoviesUseCase: GetMoviesUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, FirstFragment.newInstance())
            .addToBackStack(FirstFragment.FRAGMENT_NAME)
            .commit()

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

}