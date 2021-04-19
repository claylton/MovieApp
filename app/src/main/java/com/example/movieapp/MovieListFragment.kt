package com.example.movieapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMovieListBinding
import com.example.movieapp.model.MovieListItem
import com.github.kittinunf.fuel.Fuel
import com.google.gson.JsonParser

class MovieListFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var editText: EditText
    lateinit var movieItem: LinearLayout
    private var moviesList = ArrayList<MovieListItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val biding: FragmentMovieListBinding = androidx.databinding.DataBindingUtil.inflate(
            inflater, R.layout.fragment_movie_list,container,false
        )
        //biding.listAllMovies.setOnClickListener(
            //Navigation.createNavigateOnClickListener()
        //)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.list_all_movies)
        editText = view.findViewById(R.id.inputBuscar)
        //AQUI BUGAmovieItem = findViewById<LinearLayout>(R.id.movie_item)

        val adapterListMovie = context?.let { AdapterListMovie(it, moviesList) }

        val layoutManager = GridLayoutManager(context, 2)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapterListMovie

        editText.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            moviesList.clear()

            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch(adapterListMovie!!)
                editText.clearFocus()
                return@OnEditorActionListener true
            }
            false
        })



        //movieItem.setOnClickListener{
        //  movieItem.findNavController().navigate(R.id.details_movie)
        //}
    }

    private fun performSearch(adapterListMovie: AdapterListMovie) {

        var search = editText.text
        val link = "https://www.omdbapi.com/?i=tt3896198&apikey=69893f86&s=$search"
        //Log.d("xxx", "$search")
        Log.d("xxx", "$link")


        Fuel.get(link)
            .response { request, response, result ->
                val (bytes, error) = result
                if (bytes != null) {
                    val e = JsonParser().parse(String(bytes))
                    val obj = e.asJsonObject
                    val rm = obj.get("Search").asJsonArray

                    for (i in rm) {
                        var movie = i.asJsonObject
                        moviesList.add(
                            MovieListItem(
                                movie.get("Title").asString,
                                movie.get("Poster").asString
                            )
                        )
                        //adapterListMovie.notifyItemChanged(moviesList.indexOf(i))
                    }
                    adapterListMovie.notifyDataSetChanged()

                }

            }
    }
}