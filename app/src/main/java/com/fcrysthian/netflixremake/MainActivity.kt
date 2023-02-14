package com.fcrysthian.netflixremake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fcrysthian.netflixremake.adapter.CategoryAdapter
import com.fcrysthian.netflixremake.adapter.MovieAdapter
import com.fcrysthian.netflixremake.model.Category
import com.fcrysthian.netflixremake.model.Movie
import com.fcrysthian.netflixremake.util.CategoryTask

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val categories = mutableListOf<Category>()

        val adapter = CategoryAdapter(categories)
        val rv: RecyclerView = findViewById(R.id.rv_main)
//        rv.layoutManager = LinearLayoutManager(this) Rolagem na vertical
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false) // rolagem na horizontal
        rv.adapter = adapter

        CategoryTask().execute("https://api.tiagoaguiar.co/netflixapp/home?apiKey=9207fe2f-8803-4efc-8fbf-9e643ef62888")
    }



}