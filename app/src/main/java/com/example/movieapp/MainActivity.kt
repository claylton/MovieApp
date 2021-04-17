package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.model.MovieListItem

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var movies_list = ArrayList<MovieListItem>()


        recyclerView = findViewById<RecyclerView>(R.id.list_all_movies)

        movies_list.add(MovieListItem("Iuuuuuuuu","https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/4d2a5791-a626-4a7a-a420-2b810cb51fe0/dbz4l17-b1ad27c1-6eca-4af4-aa33-bd76c535bc1b.png/v1/fill/w_779,h_1026,q_70,strp/minato_namikaze_rassengan_by_gevdano_dbz4l17-pre.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOiIsImlzcyI6InVybjphcHA6Iiwib2JqIjpbW3siaGVpZ2h0IjoiPD0xMzQ5IiwicGF0aCI6IlwvZlwvNGQyYTU3OTEtYTYyNi00YTdhLWE0MjAtMmI4MTBjYjUxZmUwXC9kYno0bDE3LWIxYWQyN2MxLTZlY2EtNGFmNC1hYTMzLWJkNzZjNTM1YmMxYi5wbmciLCJ3aWR0aCI6Ijw9MTAyNCJ9XV0sImF1ZCI6WyJ1cm46c2VydmljZTppbWFnZS5vcGVyYXRpb25zIl19.YUVNy6zXQbWTQxlVLFw5kwiV7O3hFRktehAF47itE24"))
        movies_list.add(MovieListItem("Iuuuuuuuu","https://i.pinimg.com/originals/bd/47/49/bd4749719617555e00e5cfbf91ef76d8.jpg"))
        movies_list.add(MovieListItem("Iuuuuuuuu","https://i.pinimg.com/originals/bd/47/49/bd4749719617555e00e5cfbf91ef76d8.jpg"))
        movies_list.add(MovieListItem("Iuuuuuuuu","https://i.pinimg.com/originals/bd/47/49/bd4749719617555e00e5cfbf91ef76d8.jpg"))
        movies_list.add(MovieListItem("Iuuuuuuuu","https://i.pinimg.com/originals/bd/47/49/bd4749719617555e00e5cfbf91ef76d8.jpg"))

        val adapterListMovie = AdapterListMovie(this, movies_list)

        val layoutManager = GridLayoutManager(this,2)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapterListMovie

    }
}