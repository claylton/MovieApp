package com.example.movieapp

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.model.Movie

class AdapterListMovie( var activity: Activity, var list_movie: MutableList<Movie>) : RecyclerView.Adapter<RecycleViewHolder>() {

    //private var itemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_movie_list_item, parent, false)

        return RecycleViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        Glide
            .with(activity.applicationContext)
            .load(list_movie[position].image_movie)
            .centerCrop()
            .into(holder.image_movie)

        holder.movie_title.text = list_movie[position].title_movie

        //chegando nulo
        holder.image_movie.setOnClickListener { openMovieDetails(list_movie[position].imdbID) }
    }

    override fun getItemCount(): Int {
        return  list_movie.count()
    }

    private fun openMovieDetails(imdbID: String) {
        val bundle = Bundle()
        bundle.putString(Build.ID, imdbID)
        Navigation.findNavController(activity, R.id.myNavHostFragment)
            .navigate(R.id.action_movieListFragment_to_moviesDetailsFragment, bundle)
    }
    fun onItemClick(position: Int) {
        val movie = list_movie[position]
        openMovieDetails(movie.imdbID)
    }
}

class RecycleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var image_movie = itemView.findViewById<ImageView>(R.id.image_movie)
    var movie_title =itemView.findViewById<TextView>(R.id.movie_title)
}
