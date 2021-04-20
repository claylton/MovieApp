package com.example.movieapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.FragmentMovieDetailsBinding
import com.example.movieapp.model.MovieDetails
import com.example.movieapp.model.Ratings
import com.github.kittinunf.fuel.Fuel
import com.google.gson.JsonParser

class MoviesDetailsFragment : Fragment() {

    private lateinit var  binding: FragmentMovieDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentMovieDetailsBinding>(
            inflater, R.layout.fragment_movie_details, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var IDMovie = arguments?.getString("ID")

        findMovie(
            IDMovie!!
        ) { movieDetails ->


            /*    Glide
                    .with(view.context)
                    .load(movieDetails.Poster)
                    .centerCrop()
                    .into(image)
                Log.d("xxx","Context $context")
*/
            /*binding.titleTextView.text = movieDetails.Title
            binding.yearTextView.text = movieDetails.Year.toString()
            binding.ratedTextView.text = movieDetails.Rated
            binding.runtimeTextView.text = movieDetails.Runtime
            binding.releasedDateTextView.text = movieDetails.Released
            binding.metaScoreTextView.text = movieDetails.Metascore
            binding.plotTextView.text = movieDetails.Plot
            binding.imdbRatingTextView.text = movieDetails.imdbRating
            binding.imdbVotesTextView.text = movieDetails.imdbVotes
            binding.directorTextView.text = movieDetails.Director*/
            requireActivity().runOnUiThread {
                kotlin.run {
                    var image: ImageView = view.findViewById<ImageView>(R.id.movie_poster_image_view)
                    Glide
                        .with(view.context)
                        .load(movieDetails.Poster)
                        .centerCrop()
                        .into(image)
                    
                    view.findViewById<TextView>(R.id.title_text_view).text = movieDetails.Title
                    view.findViewById<TextView>(R.id.year_text_view).text =
                        movieDetails.Year.toString()
                    view.findViewById<TextView>(R.id.rated_text_view).text = movieDetails.Rated
                    view.findViewById<TextView>(R.id.runtime_text_view).text = movieDetails.Runtime
                    view.findViewById<TextView>(R.id.released_date_text_view).text =
                        movieDetails.Released
                    view.findViewById<TextView>(R.id.meta_score_text_view).text =
                        movieDetails.Metascore
                    view.findViewById<TextView>(R.id.plot_text_view).text = movieDetails.Plot
                    view.findViewById<TextView>(R.id.imdb_rating_text_view).text =
                        movieDetails.imdbRating
                    view.findViewById<TextView>(R.id.imdb_votes_text_view).text =
                        movieDetails.imdbVotes
                    view.findViewById<TextView>(R.id.director_text_view).text =
                        movieDetails.Director
                    view.findViewById<TextView>(R.id.writer_text_view).text = movieDetails.Writer
                    view.findViewById<TextView>(R.id.actors_text_view).text = movieDetails.Actors
                    view.findViewById<TextView>(R.id.language_text_view).text =
                        movieDetails.Language
                    view.findViewById<TextView>(R.id.country_text_view).text = movieDetails.Country
                    view.findViewById<TextView>(R.id.genre_text_view).text = movieDetails.Genre
                    view.findViewById<TextView>(R.id.awards_text_view).text = movieDetails.Awards
                }
            }
        }

        Log.d("xxx", "ID MOVIE: $IDMovie")
        Log.d("xxx", "ARGS: $arguments")
    }

    private fun findMovie(idMovie: String, funcCallback: (MovieDetails) -> Unit) {

        val link = "https://www.omdbapi.com/?apikey=69893f86&i=$idMovie"
        //Log.d("xxx", "$search")
        Log.d("xxx", "$link")


        Fuel.get(link)
            .response { request, response, result ->
                val (bytes, error) = result
                if (bytes != null) {
                    val e = JsonParser().parse(String(bytes))
                    val movie = e.asJsonObject

                    Log.d("xxx","Movie $movie")
                    val JSONRatings = movie.get("Ratings").asJsonArray

                    val listRatings: ArrayList<Ratings> = ArrayList()

                    for (i in JSONRatings){
                        var ratings = i.asJsonObject
                        listRatings.add(Ratings(
                            ratings.get("Source").asString,
                            ratings.get("Value").asString))
                    }

                    val movieDetails = MovieDetails(
                        movie.get ("imdbID").asString,
                        movie.get("Title").asString,
                        movie.get("Year").asInt,
                        movie.get("Poster").asString,
                        movie.get("Rated").asString,
                        movie.get("Released").asString,
                        movie.get("Runtime").asString,
                        movie.get("Genre").asString,
                        movie.get("Director").asString,
                        movie.get("Writer").asString,
                        movie.get("Actors").asString,
                        movie.get("Plot").asString,
                        movie.get("Language").asString,
                        movie.get("Country").asString,
                        movie.get("Awards").asString,
                        movie.get("Metascore").asString,
                        movie.get("imdbRating").asString,
                        movie.get("imdbVotes").asString,
                        listRatings
                    )

                    Log.d("xxx","ANTES")
                    funcCallback(movieDetails)
                    Log.d("xxx","DEPOIS")


                }

            }
    }
}