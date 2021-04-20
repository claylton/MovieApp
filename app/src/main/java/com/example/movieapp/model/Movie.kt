package com.example.movieapp.model

import android.widget.ImageView

data class Movie(
    var imdbID: String,
    var title_movie: String,
    var image_movie: String
    )

data class MovieDetails(
    var imdbID: String,
    var Title: String,
    var Year: Int,
    var Poster: String,
    var Rated: String,
    var Released: String,
    var Runtime: String,
    var Genre: String,
    var Director: String,
    var Writer: String,
    var Actors: String,
    var Plot: String,
    var Language: String,
    var Country: String,
    var Awards: String,
    var Metascore: String,
    var imdbRating: String,
    var imdbVotes: String,
    var Ratings: ArrayList<Ratings>
)

data class Ratings(
    var Source: String,
    var Value: String
)