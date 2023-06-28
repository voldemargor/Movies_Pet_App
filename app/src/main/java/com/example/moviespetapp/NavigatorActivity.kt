package com.example.moviespetapp

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.manager.SupportRequestManagerFragment
import com.example.moviespetapp.databinding.ActivityNavigatorBinding
import com.example.moviespetapp.presentation.bookmarks.BookmarksFragment
import com.example.moviespetapp.presentation.contract.BottomNavItem
import com.example.moviespetapp.presentation.contract.HasBackIcon
import com.example.moviespetapp.presentation.contract.Navigator
import com.example.moviespetapp.presentation.contract.GetFromBackstack
import com.example.moviespetapp.presentation.mainscreen.MainFragment
import com.example.moviespetapp.presentation.moviedetails.MovieDetailsFragment
import com.example.moviespetapp.presentation.movieslist.MoviesListScreenFragment
import com.example.moviespetapp.presentation.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigatorActivity : AppCompatActivity(), Navigator {

    private val viewModel by viewModels<NavigatorActivityViewModel>()

    private lateinit var fragmentLifecycleListener: FragmentManager.FragmentLifecycleCallbacks
    private lateinit var binding: ActivityNavigatorBinding
    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigatorBinding.inflate(layoutInflater).also { setContentView(it.root) }

        observeViewModel()
        setBottomNavListener()
        setFragmentLifecycleListener()
        setBarColors()
        displayMainScreen()
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

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1)
            onBackPressedDispatcher.onBackPressed()
        else finish()
    }

    private fun observeViewModel() {
        viewModel.displaySplash.observe(this) {
            if (it) displaySplash()
            else hideSplash()
        }
    }

    override fun displayMainScreen() {
        val fragment = supportFragmentManager.findFragmentByTag(MainFragment.FRAGMENT_TAG)
        log("MainFragment = $fragment")

        if (fragment == null)
            MainFragment.newInstance().also { launchFragment(it) }
        else
            launchFragment(fragment)
        //supportFragmentManager.popBackStack(SearchFragment.FRAGMENT_TAG, 0)
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
        BookmarksFragment.newInstance().also {
            launchFragment(it)
        }
    }

    override fun displaySearchScreen() {
        val fragment = supportFragmentManager.findFragmentByTag(SearchFragment.FRAGMENT_TAG)
        log("SearchFragment = $fragment")

        if (fragment == null)
            SearchFragment.newInstance().also { launchFragment(it) }
        else
            launchFragment(fragment)
        //supportFragmentManager.popBackStack(SearchFragment.FRAGMENT_TAG, 0)
    }

    override fun goBack() {
        onBackPressed()
    }

    override fun setScreenTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun displaySplash() {
        supportActionBar?.hide()
        binding.splashLayout.root.visibility = View.VISIBLE
        binding.bottomNav.visibility = View.GONE
        binding.splashLayout.ivSplashScrIcon.startAnimation(
            AlphaAnimation(0.2f, 1f).apply {
                duration = 600
                repeatMode = Animation.REVERSE
                //interpolator = LinearInterpolator()
                repeatCount = Animation.INFINITE
            })
    }

    private fun hideSplash() {
        supportActionBar?.show()
        binding.splashLayout.root.visibility = View.GONE
        binding.bottomNav.visibility = View.VISIBLE
        binding.splashLayout.ivSplashScrIcon.clearAnimation()
    }

    private fun updateUI(fragment: Fragment) {
        log("updateUI: ${fragment.javaClass.simpleName}")
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

    private fun setBarColors() {
        supportActionBar?.setBackgroundDrawable(
            ColorDrawable(
                resources.getColor(
                    R.color.background,
                    null
                )
            )
        )
        window.statusBarColor = getColor(R.color.background)
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

    private fun launchFragment(fragment: Fragment) {
        if (isDoubleBottomNavClick(fragment)) return
        // TODO При повторном клике нужно скроллить наверх

        var tag: String? = null
        if (fragment is GetFromBackstack) tag = fragment.getFragmentTag()

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                androidx.appcompat.R.anim.abc_fade_in,
                androidx.appcompat.R.anim.abc_fade_out
            )
            .replace(R.id.fragmentContainer, fragment, tag)
            .setReorderingAllowed(true)
            .addToBackStack(tag)
            .commit()
    }

    private fun isDoubleBottomNavClick(fragment: Fragment): Boolean {
        if (currentFragment is BottomNavItem && fragment !is BottomNavItem) return false
        if (currentFragment !is BottomNavItem && fragment is BottomNavItem) return false
        if (fragment is BottomNavItem && fragment == currentFragment) return true
        return false
    }

    override fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun log(message: String) {
        Log.d("mylog", message)
    }

}

