package com.example.moviespetapp.presentation

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.moviespetapp.R
import com.example.moviespetapp.domain.entity.Movie
import com.example.moviespetapp.domain.entity.Trailer
import com.example.moviespetapp.domain.entity.Votes
import com.example.moviespetapp.presentation.moviedetails.YoutubeTrailer
import jp.wasabeef.glide.transformations.BlurTransformation
import org.apache.commons.lang3.text.StrBuilder
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.math.round

class Utils {

    companion object {

        fun getRatingRounded(value: Double?): String {
            val rating = value ?: 0.0
            val rounded = round(rating * 10) / 10
            if (rating == 0.0) return "—"
            return rounded.toString()
        }

        fun getRatingBgColor(rating: String): Int {
            if (rating.toFloat() >= 7)
                return R.color.rating_bg_positive
            return R.color.rating_bg_neutral
        }

        fun getRatingTextColor(context: Context, rating: String): Int {
            if (rating.toFloat() >= 7)
                return ContextCompat.getColor(context, R.color.rating_bg_positive)
            return ContextCompat.getColor(context, R.color.rating_text_neutral)
        }

        fun getVotesInKilos(votes: Votes): String {
            return (votes.kp.toInt() / 1000).toString() + "K"
        }

        fun getVotesFormatted(number: Int): String {
            return String.format("%,d", number)
        }

        fun getStringYearAndGenres(movie: Movie): String {
            val builder = StrBuilder()
            builder.append(movie.year)
            if (movie.genres.isNullOrEmpty()) return builder.toString()
            builder.append(", ")

            val genres = movie.genres
            for (i in genres.indices) {
                builder.append(genres[i].name)
                if (i < genres.size - 1)
                    builder.append(", ")
            }
            return builder.toString()
        }

        fun getStringCountryDurationAgeRating(movie: Movie): String {
            val builder = StrBuilder()
            builder.append(movie.country.name.toString())
            if (movie.movieLength != null) builder.append(", " + getStringDuration(movie.movieLength))
            if (movie.ageRating != null) builder.append(", " + movie.ageRating + "+")
            return builder.toString()
        }

        fun ImageView.loadImage(url: String) {
            Glide.with(this)
                .load(url)
                //.placeholder(R.drawable.drawable)
                .error(R.drawable.ic_home)
                .transition(DrawableTransitionOptions.withCrossFade())
                .optionalFitCenter()
                .into(this)
        }

        fun ImageView.loadBlurredImage(url: String) {
            Glide.with(this)
                .load(url)
                .apply(RequestOptions.bitmapTransform(BlurTransformation(40, 3)))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(this)
        }

        fun getYoutubeTrailer(trailers: List<Trailer>?): YoutubeTrailer? {
            if (trailers.isNullOrEmpty())
                return null

            var selectedTrailer: Trailer? = null
            for (trailer in trailers) {
                if (trailer.site.equals("youtube")) {
                    selectedTrailer = trailer
                    break
                }
            }

            if (selectedTrailer != null) {
                val id = getYoutubeId(selectedTrailer.url)
                val urlPreview = "https://img.youtube.com/vi/" + id + "/0.jpg"
                val urlVideo = "https://youtu.be/" + id
                return YoutubeTrailer(urlPreview, urlVideo)
            }

            return null
        }

        private fun getYoutubeId(youTubeUrl: String): String {
            val pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*"
            val compiledPattern: Pattern = Pattern.compile(pattern)
            val matcher: Matcher = compiledPattern.matcher(youTubeUrl)
            return if (matcher.find()) {
                matcher.group()
            } else {
                "error"
            }
        }

        private fun getStringDuration(duration: Int): String {
            val hh: Int = duration / 60
            val mm = duration - hh * 60
            return "$hh ч $mm мин"
        }

        //fun convertPixelsToDp(pixels: Float, resources: Resources): Int {
        //    return TypedValue.applyDimension(
        //        /* unit = */ TypedValue.COMPLEX_UNIT_DIP,
        //        /* value = */ MainFragment.HORIZONTAL_ITEM_WIDTH,
        //        /* metrics = */ resources.displayMetrics).toInt()
        //}


    }

}