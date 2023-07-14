package com.example.moviespetapp

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.manager.SupportRequestManagerFragment
import com.example.moviespetapp.databinding.ActivityNavigatorBinding
import com.example.moviespetapp.presentation.bookmarks.BookmarksFragment
import com.example.moviespetapp.presentation.contract.*
import com.example.moviespetapp.presentation.dialog.*
import com.example.moviespetapp.presentation.mainscreen.MainFragment
import com.example.moviespetapp.presentation.moviedetails.MovieDetailsFragment
import com.example.moviespetapp.presentation.movieslist.MoviesListScreenFragment
import com.example.moviespetapp.presentation.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigatorActivity : AppCompatActivity(), Navigator {

    private val viewModel by viewModels<NavigatorActivityViewModel>()
    private val connection by lazy { NetworkConnectionService(this) }

    private lateinit var binding: ActivityNavigatorBinding
    private lateinit var fragmentLifecycleListener: FragmentManager.FragmentLifecycleCallbacks

    private var lastCallFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this, getOnBackPressedCallback())
        binding = ActivityNavigatorBinding.inflate(layoutInflater).also { setContentView(it.root) }

        observeViewModel()
        setBottomNavListener()
        setFragmentLifecycleListener()
        setBarColors()
        displayScreen(Main())
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

    private fun observeViewModel() {
        viewModel.displaySplash.observe(this) {
            if (it) displaySplash()
            else hideSplash()
        }
    }

    override fun displayScreen(scr: Screen) {
        val fragment: Fragment = when (scr) {
            is Main -> MainFragment.newInstance()
            is Bookmarks -> BookmarksFragment.newInstance()
            is Search -> SearchFragment.newInstance()
            is MoviesList -> MoviesListScreenFragment.newInstance(scr.genreName)
            is MovieDetails -> MovieDetailsFragment.newInstance(scr.movieId, scr.movieName)
        }
        lastCallFragment = fragment
        screenTransaction(fragment)
    }

    private fun screenTransaction(newFragment: Fragment) {
        if (connection.shouldDisplayNoInternet())
            return

        var backstackInstance: Fragment? = null

        if (newFragment is GetFromBackstack) backstackInstance =
            supportFragmentManager.findFragmentByTag(newFragment.getFragmentTag())

        if (backstackInstance == null) launchFragment(newFragment)
        else launchFragment(backstackInstance)
    }

    private fun launchFragment(fragment: Fragment) {
        var tag: String? = null
        if (fragment is GetFromBackstack) tag = fragment.getFragmentTag()

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                androidx.appcompat.R.anim.abc_fade_in,
                androidx.appcompat.R.anim.abc_fade_out
            )
            .setReorderingAllowed(true)
            .replace(R.id.fragmentContainer, fragment, tag)
            .addToBackStack(tag)
            .commit()

        for (fr in supportFragmentManager.fragments) {
            log("Fragments in container: ${fr::class.simpleName.toString()}")
        }
    }

    override fun goBack() {
        onBackPressedDispatcher.onBackPressed()
    }

    override fun setScreenTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun displayExceptionDialog(message: String) {
        ExceptionDialog.newInstance(message).show(supportFragmentManager, ExceptionDialog.TAG)
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

    override fun tryReconnect() {
        if (connection.hasInternetConnection())
            lastCallFragment?.let { screenTransaction(it) }
        else connection.displayNoInternetDialog()
    }

    private fun getOnBackPressedCallback() =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.backStackEntryCount <= 1)
                    finish()
                else if (connection.shouldDisplayNoInternet())
                    return
                supportFragmentManager.popBackStack()
            }
        }

    private fun setFragmentLifecycleListener() {
        fragmentLifecycleListener = object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentStarted(fm: FragmentManager, fragment: Fragment) {
                super.onFragmentStarted(fm, fragment)
                if (fragment is SupportRequestManagerFragment) return
                if (fragment is DialogFragment) return
                updateUI(fragment)
            }
        }
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentLifecycleListener, false)
    }

    private fun setBottomNavListener() {
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navItemFirstScreen -> displayScreen(Main())
                R.id.navItemBookmark -> displayScreen(Bookmarks())
                R.id.navItemSearch -> displayScreen(Search())
            }
            true
        }
    }

    override fun log(message: String) {
        Log.d("mylog", message)
    }

    override fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}

