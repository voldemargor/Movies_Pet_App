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

    private var _bookedIDs = mutableSetOf<String>()
    val bookedIDs: Array<String> get() = _bookedIDs.toTypedArray()

    init {
        loadIDs()
    }

    fun hasBookmark(movieId: Int?) =
        _bookedIDs.contains(movieId.toString())

    fun addToBookmarks(movieId: Int) {
        _bookedIDs.add(movieId.toString())
        saveSharedPreferences()
    }

    fun removeBookmark(movieId: Int) {
        _bookedIDs.remove(movieId.toString())
        saveSharedPreferences()
    }

    private fun loadIDs() {
        _bookedIDs =
            preferences.getStringSet(STRING_SET_KEY, mutableSetOf()) as MutableSet<String>
    }

    private fun saveSharedPreferences() {
        val save = _bookedIDs.toSet()
        preferences.edit()
            .clear()
            .putStringSet(STRING_SET_KEY, save)
            .apply()
    }

    companion object {

        const val BOOKMARKS_SHARED_PREFS = "bookmarks_prefs"
        const val STRING_SET_KEY = "bookmarks_value_key"

    }

}