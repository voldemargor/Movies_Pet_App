package com.example.moviespetapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.moviespetapp.databinding.ActivityMainBinding
import com.example.moviespetapp.presentation.bookmark.BookmarkFragment
import com.example.moviespetapp.presentation.MainActivityViewModel
import com.example.moviespetapp.presentation.MainFragment
import com.example.moviespetapp.presentation.moviedetails.MovieDetailsFragment
import com.example.moviespetapp.presentation.movieslist.MoviesListScreenFragment
import com.example.moviespetapp.presentation.search.SearchFragment
import com.example.moviespetapp.presentation.contract.BottomNavItem
import com.example.moviespetapp.presentation.contract.HasBackIcon
import com.example.moviespetapp.presentation.contract.HasCustomTitle
import com.example.moviespetapp.presentation.contract.Navigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainActivityViewModel>()
    private lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        observeViewModel()
        setListeners()
        if (savedInstanceState == null) displayMainScreenFirstTime()
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

    private fun displayMainScreenFirstTime() {
        val fragment = MainFragment.newInstance()
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
        MainFragment.newInstance().also {
            launchFragment(it)
            updateUi(it)
        }
    }

    override fun displayMoviesListScreen(genreName: String) {
        MoviesListScreenFragment.newInstance(genreName).also {
            launchFragment(it)
            updateUi(it)
        }
    }

    override fun displayMovieDetailsScreen(movieId: Int, movieName: String) {
        MovieDetailsFragment.newInstance(movieId, movieName).also {
            launchFragment(it)
            updateUiForMovieDetails(it, movieName)
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
        onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        // Клик по кнопке назад в action bar
        onBackPressed()
        Log.d("mylog", "onSupportNavigateUp()")
        return true
    }

    override fun onBackPressed() {
        onBackPressedDispatcher.onBackPressed()
        val fragment = supportFragmentManager.fragments.get(0)
        updateUi(fragment)
        if (fragment is MovieDetailsFragment)
            updateUiForMovieDetails(fragment, fragment.movieName)
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    //override fun onOptionsItemSelected(item: MenuItem): Boolean {
    //    // Клик по кнопке назад в action bar
    //    if (item.itemId == android.R.id.home) {
    //        onBackPressed()
    //        return true
    //    }
    //    return false
    //}

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
        if (fragment is HasCustomTitle)
            setTitle(getString(fragment.getScreenTitleRes()))
        updateBackIcon(fragment)
        updateBottomNavSelection(fragment)
    }

    private fun updateUiForMovieDetails(fragment: Fragment, movieName: String?) {
        setTitle(movieName)
        updateBackIcon(fragment)
        updateBottomNavSelection(fragment)
    }

    private fun updateBackIcon(fragment: Fragment) {
        Log.d("mylog", "updateBackIcon")
        if (fragment is HasBackIcon) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(false)
        }
    }

    private fun updateBottomNavSelection(fragment: Fragment) {
        val menu = binding.bottomNav.menu
        if (fragment is BottomNavItem) {
            menu.setGroupCheckable(0, true, true)
            menu.findItem(fragment.getBottomNavItemId()).isChecked = true
        } else
            menu.setGroupCheckable(0, false, true)
    }

    private fun setTitle(movieName: String?) {
        supportActionBar?.title = movieName
    }


}