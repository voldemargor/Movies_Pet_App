package com.example.moviespetapp

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.moviespetapp.databinding.ActivityMainBinding
import com.example.moviespetapp.presentation.BookmarkFragment
import com.example.moviespetapp.presentation.FirstFragment
import com.example.moviespetapp.presentation.MainActivityViewModel
import com.example.moviespetapp.presentation.MovieDetailsFragment
import com.example.moviespetapp.presentation.SearchFragment
import com.example.moviespetapp.presentation.contract.BottomNavItem
import com.example.moviespetapp.presentation.contract.HasBackIcon
import com.example.moviespetapp.presentation.contract.HasCustomTitle
import com.example.moviespetapp.presentation.contract.Navigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding

    //@Inject lateinit var repository: Repository
    //@Inject lateinit var getMoviesUseCase: GetMoviesUseCase

    private val viewModel by viewModels<MainActivityViewModel>()
    private lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        observeViewModel()
        setListeners()
        if (savedInstanceState == null) displayMainScreenFirstTime()

        //val fragments = supportFragmentManager.fragments
        //Log.d("mylog", "Fragments: ${fragments.size}")


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

    private fun observeViewModel() {
        viewModel.displaySplash.observe(this) {
            binding.splashLayout.visibility = View.GONE
            supportActionBar?.show()
            if (it) {
                binding.splashLayout.visibility = View.VISIBLE
                supportActionBar?.hide()
                window.statusBarColor = getColor(R.color.black)
            }
        }
    }

    private fun setListeners() {
        binding.bottomNav.setOnItemSelectedListener() {
            when (it.itemId) {
                R.id.navItemFirstScreen -> displayMainScreen()
                R.id.navItemBookmark -> displayBookmarksScreen()
                R.id.navItemSearch -> displaySearchScreen()
            }
            true
        }
    }

    //override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    //    // we've called setSupportActionBar in onCreate,
    //    // that's why we need to override this method too
    //    updateUi()
    //    return true
    //}

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun displayMainScreenFirstTime() {
        val fragment = FirstFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                androidx.appcompat.R.anim.abc_fade_in,
                androidx.appcompat.R.anim.abc_fade_out)
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
        currentFragment = fragment
        updateUi(fragment)
    }

    override fun displayMainScreen() {
        FirstFragment.newInstance().also {
            launchFragment(it)
            updateUi(it)
        }
    }

    override fun displayMovieDetailsScreen(movieId: Int) {
        MovieDetailsFragment.newInstance(movieId).also {
            launchFragment(it)
            updateUi(it)
        }
    }

    override fun displayBookmarksScreen() {
        BookmarkFragment.newInstance().also {
            launchFragment(it)
            updateUi(it)
        }
    }

    override fun displaySearchScreen() {
        SearchFragment.newInstance().also {
            launchFragment(it)
            updateUi(it)
        }
    }

    override fun displaySearchResultsScreen() {
        TODO("Not yet implemented")
    }

    override fun goBack() {
        Toast.makeText(this, "MainActivity: goBack()", Toast.LENGTH_SHORT).show()
        onBackPressedDispatcher.onBackPressed()
    }

    override fun onBackPressed() {
        onBackPressedDispatcher.onBackPressed()
        Toast.makeText(this, "MainActivity: onBackPressed()", Toast.LENGTH_SHORT).show()
        val fragment = supportFragmentManager.fragments.get(0)
        updateUi(fragment)
    }

    private fun launchFragment(fragment: Fragment) {

        if (isRepeatedMenuClick(fragment)) return // TODO Нужно скроллить наверх

        currentFragment = fragment
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                androidx.appcompat.R.anim.abc_fade_in,
                androidx.appcompat.R.anim.abc_fade_out)
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun isRepeatedMenuClick(fragment: Fragment): Boolean {
        if (fragment is BottomNavItem)
            if (fragment.getBottomNavItemId() == binding.bottomNav.selectedItemId)
                return true
        return false
    }

    private fun updateUi(fragment: Fragment) {
        setTitle(fragment)
        updateBackIcon(fragment)
        updateBottomNavSelection(fragment)
    }

    private fun updateBackIcon(fragment: Fragment) {
        if (fragment is HasBackIcon) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(false)
        }
    }

    private fun setTitle(fragment: Fragment) {
        if (fragment is HasCustomTitle)
            supportActionBar?.title = getString(fragment.getTitleRes())
        else
            supportActionBar?.title = getString(R.string.app_name)
    }

    private fun updateBottomNavSelection(fragment: Fragment) {
        val menu = binding.bottomNav.menu
        if (fragment is BottomNavItem) {
            menu.setGroupCheckable(0, true, true)
            menu.findItem(fragment.getBottomNavItemId()).isChecked = true
        } else
            menu.setGroupCheckable(0, false, true)
    }


}