package com.example.moviespetapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.manager.SupportRequestManagerFragment
import com.example.moviespetapp.databinding.ActivityMainBinding
import com.example.moviespetapp.presentation.MainActivityViewModel
import com.example.moviespetapp.presentation.MainFragment
import com.example.moviespetapp.presentation.bookmark.BookmarkFragment
import com.example.moviespetapp.presentation.contract.BottomNavItem
import com.example.moviespetapp.presentation.contract.HasBackIcon
import com.example.moviespetapp.presentation.contract.Navigator
import com.example.moviespetapp.presentation.moviedetails.MovieDetailsFragment
import com.example.moviespetapp.presentation.movieslist.MoviesListScreenFragment
import com.example.moviespetapp.presentation.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Navigator {

    private val viewModel by viewModels<MainActivityViewModel>()

    private lateinit var fragmentLifecycleListener: FragmentManager.FragmentLifecycleCallbacks
    private lateinit var binding: ActivityMainBinding
    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        observeViewModel()
        setBottomNavListener()
        setFragmentLifecycleListener()
        if (savedInstanceState == null)
            displayFirstScreen()
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentLifecycleListener)
    }

    override fun onSupportNavigateUp(): Boolean {
        // Клик по кнопке назад в action bar
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun onBackPressed() = onBackPressedDispatcher.onBackPressed()

    //private fun handleBackPressed() {
    //    onBackPressedDispatcher.onBackPressed()
    //    //val fragment = supportFragmentManager.findFragmentById(binding.fragmentContainer.id)
    //    //updateUi(fragment ?: throw RuntimeException("fragment is NULL"))
    //    //log("updateUi 1")
    //    //fragment.onResume()
    //    //currentFragment = fragment
    //    log("Живых фрагментов в контейнере: ${supportFragmentManager.fragments.count()}")
    //}

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

    private fun setBottomNavListener() {
        binding.bottomNav.setOnItemSelectedListener() {
            when (it.itemId) {
                R.id.navItemFirstScreen -> displayMainScreen()
                R.id.navItemBookmark -> displayBookmarksScreen()
                R.id.navItemSearch -> displaySearchScreen()
            }
            true
        }
    }

    private fun setFragmentLifecycleListener() {
        fragmentLifecycleListener = object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentStarted(fm: FragmentManager, fragment: Fragment) {
                super.onFragmentStarted(fm, fragment)
                if (fragment is SupportRequestManagerFragment) return
                updateUI(fragment)
                currentFragment = fragment
            }
        }
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentLifecycleListener, false)
    }

    private fun displayFirstScreen() {
        val fragment = MainFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                androidx.appcompat.R.anim.abc_fade_in,
                androidx.appcompat.R.anim.abc_fade_out)
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    override fun displayMainScreen() {
        MainFragment.newInstance().also {
            launchFragment(it)
        }
    }

    override fun displayMoviesListScreen(genreName: String) {
        MoviesListScreenFragment.newInstance(genreName).also {
            launchFragment(it)
        }
    }

    override fun displayMovieDetailsScreen(movieId: Int, movieName: String) {
        MovieDetailsFragment.newInstance(movieId, movieName).also {
            launchFragment(it)
        }
    }

    override fun displayBookmarksScreen() {
        BookmarkFragment.newInstance().also {
            launchFragment(it)
        }
    }

    override fun displaySearchScreen() {
        SearchFragment.newInstance().also {
            launchFragment(it)
        }
    }

    override fun displaySearchResultsScreen() {
        TODO("Not yet implemented")
    }

    override fun goBack() {
        onBackPressed()
    }

    override fun setScreenTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun log(message: String) {
        Log.d("mylog", message)
    }

    private fun launchFragment(fragment: Fragment) {

        if (isRepeatedMenuClick(fragment)) return
        // TODO При повторном клике нужно скроллить наверх

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                androidx.appcompat.R.anim.abc_fade_in,
                androidx.appcompat.R.anim.abc_fade_out)
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()

        log("Живых фрагментов в контейнере: ${supportFragmentManager.fragments.count()}")
    }

    private fun isRepeatedMenuClick(fragment: Fragment): Boolean {

        if (currentFragment is BottomNavItem && fragment !is BottomNavItem) return false
        if (currentFragment !is BottomNavItem && fragment is BottomNavItem) return false

        if (fragment is BottomNavItem)
            if (fragment.getBottomNavItemId() == binding.bottomNav.selectedItemId)
                return true
        return false
    }

    private fun updateUI(fragment: Fragment) {
        //if (fragment is SupportRequestManagerFragment) return
        //log("updateUI")
        log("updateUI arg fragment: ${fragment.javaClass.simpleName}")
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

    private fun updateBottomNavSelection(fragment: Fragment) {
        val menu = binding.bottomNav.menu
        if (fragment is BottomNavItem) {
            menu.setGroupCheckable(0, true, true)
            menu.findItem(fragment.getBottomNavItemId()).isChecked = true
        } else
            menu.setGroupCheckable(0, false, true)
    }

}