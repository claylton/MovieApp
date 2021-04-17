package com.example.movieapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.model.MovieListItem

class AdapterListMovie( var context: Context, var list_movie: List<MovieListItem>) : RecyclerView.Adapter<RecycleViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)

        return RecycleViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        Glide
            .with(context)
            .load(list_movie[position].image_movie)
            .centerCrop()
            .into(holder.image_movie)

        holder.movie_title.text = list_movie[position].title_movie
    }

    override fun getItemCount(): Int {
        return  list_movie.count()
    }

}

class RecycleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var image_movie = itemView.findViewById<ImageView>(R.id.image_movie)
    var movie_title =itemView.findViewById<TextView>(R.id.movie_title)

}