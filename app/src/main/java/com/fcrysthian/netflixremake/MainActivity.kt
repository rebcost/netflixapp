package com.fcrysthian.netflixremake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.fcrysthian.netflixremake.adapter.MainAdapter
import com.fcrysthian.netflixremake.model.Movie

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movies = mutableListOf<Movie>()
        for (i in 0 until 6){
            val movie = Movie(R.drawable.movie)
            movies.add(movie)
        }

        val adapter = MainAdapter(movies)
        val rv: RecyclerView = findViewById(R.id.rvMain)
//        rv.layoutManager = LinearLayoutManager(this) Rolagem na vertical
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false) // rolagem na horizontal
        rv.adapter = adapter
    }



}