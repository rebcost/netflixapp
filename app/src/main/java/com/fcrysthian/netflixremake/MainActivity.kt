package com.fcrysthian.netflixremake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fcrysthian.netflixremake.adapter.CategoryAdapter
import com.fcrysthian.netflixremake.adapter.MovieAdapter
import com.fcrysthian.netflixremake.model.Category
import com.fcrysthian.netflixremake.model.Movie

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val categories = mutableListOf<Category>()
        for (j in 0 until 10){
            val movies = mutableListOf<Movie>()
            for (i in 0 until 6){
                val movie = Movie(R.drawable.movie)
                movies.add(movie)
            }
            val category = Category("cat.$j", movies)
            categories.add(category)
        }


        val adapter = CategoryAdapter(categories)
        val rv: RecyclerView = findViewById(R.id.rv_main)
//        rv.layoutManager = LinearLayoutManager(this) Rolagem na vertical
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false) // rolagem na horizontal
        rv.adapter = adapter
    }



}