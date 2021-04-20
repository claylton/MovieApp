package com.example.movieapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.FragmentMovieListBinding
import com.example.movieapp.model.Movie
import com.github.kittinunf.fuel.Fuel
import com.google.gson.JsonParser


class MovieListFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var editText: EditText
    var moviesList = ArrayList<Movie>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val biding: FragmentMovieListBinding = androidx.databinding.DataBindingUtil.inflate(
            inflater, R.layout.fragment_movie_list, container, false
        )
        return biding.root
    }

    override fun onResume() {
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.list_all_movies)
        editText = view.findViewById(R.id.inputBuscar)

        val adapterListMovie = activity?.let { it -> AdapterListMovie(it, moviesList) }
        val layoutManager = GridLayoutManager(context, 2)

        Log.d("xxx", "AdapterList Movie $adapterListMovie")
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapterListMovie

        editText.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            moviesList.clear()
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch(adapterListMovie!!)
                editText.clearFocus()
                Log.d("xxx", "AdapterList Movie1 $adapterListMovie")
                Log.d("xxx", "MovieLisAdapterSearch: $moviesList")
                return@OnEditorActionListener true
            }
            false
        })
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
                        val movie = i.asJsonObject
                        moviesList.add(
                            Movie(
                                movie.get("imdbID").asString,
                                movie.get("Title").asString,
                                movie.get("Poster").asString
                            )
                        )
                    }

                    Log.d("xxx", "List $moviesList")
                    Log.d("xxx", "List notify data set changed ${adapterListMovie}")
                    //adapterListMovie.notifyDataSetChanged()
                    //recyclerView.adapter?.notifyDataSetChanged()
                }
                requireActivity().runOnUiThread {
                    kotlin.run {
                        recyclerView.adapter?.notifyDataSetChanged()
                    }
                }

            }
    }
}