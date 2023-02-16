package com.fcrysthian.netflixremake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fcrysthian.netflixremake.adapter.CategoryAdapter
import com.fcrysthian.netflixremake.adapter.MovieAdapter
import com.fcrysthian.netflixremake.model.Category
import com.fcrysthian.netflixremake.model.Movie
import com.fcrysthian.netflixremake.util.CategoryTask

class MainActivity : AppCompatActivity(), CategoryTask.Callback {

    private lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progress = findViewById(R.id.progress_main)

        val categories = mutableListOf<Category>()

        val adapter = CategoryAdapter(categories)
        val rv: RecyclerView = findViewById(R.id.rv_main)
//        rv.layoutManager = LinearLayoutManager(this) Rolagem na vertical
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false) // rolagem na horizontal
        rv.adapter = adapter

        CategoryTask(this).execute("https://api.tiagoaguiar.co/netflixapp/home?apiKey=9207fe2f-8803-4efc-8fbf-9e643ef62888")
    }

    override fun onResult(categories: List<Category>) {
        //
        Log.i("Teste MainActivity", categories.toString())
        Toast.makeText(this, "Conexão estabelecida", Toast.LENGTH_SHORT).show()
        progress.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        progress.visibility = View.GONE
    }

    override fun onPreExecute() {
        progress.visibility = View.VISIBLE
    }


}