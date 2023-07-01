package com.example.moviespetapp.presentation

sealed class Screen

class Main : Screen()
class Bookmarks : Screen()
class Search : Screen()
class MoviesList(val genreName: String) : Screen()
class MovieDetails(val movieId: Int, val movieName: String) : Screen()

