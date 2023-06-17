package com.example.moviespetapp.data.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import com.example.moviespetapp.App
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookmarkService @Inject constructor(

    private val context: App

) {

    private var preferences: SharedPreferences =
        context.getSharedPreferences(BOOKMARKS_SHARED_PREFS, Context.MODE_PRIVATE)
    private var bookmarks = mutableSetOf<String>()

    init {
        bookmarks =
            preferences.getStringSet(BOOKMARKS_VALUE_KEY, mutableSetOf()) as MutableSet<String>
    }

    fun hasBookmark(movieId: Int?) =
        bookmarks.contains(movieId.toString())

    fun addToBookmarks(movieId: Int) {
        bookmarks.add(movieId.toString())
        saveSharedPreferences()
    }

    fun removeBookmark(movieId: Int) {
        bookmarks.remove(movieId.toString())
        saveSharedPreferences()
    }

    private fun saveSharedPreferences() {
        preferences.edit()
            .putStringSet(BOOKMARKS_VALUE_KEY, bookmarks.toSet())
            .apply()
    }

    companion object {

        const val BOOKMARKS_SHARED_PREFS = "bookmarks_prefs"
        const val BOOKMARKS_VALUE_KEY = "bookmarks_value_key"

    }

}