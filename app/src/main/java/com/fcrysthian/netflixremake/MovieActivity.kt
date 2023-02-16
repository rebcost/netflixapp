package com.fcrysthian.netflixremake

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.LayerDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.netflixremake.util.MovieTask
import com.fcrysthian.netflixremake.adapter.MovieAdapter
import com.fcrysthian.netflixremake.model.Movie
import com.fcrysthian.netflixremake.model.MovieDetail
import com.fcrysthian.netflixremake.util.CategoryTask

class MovieActivity : AppCompatActivity(), MovieTask.Callback {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        val txtTitle: TextView = findViewById(R.id.movie_txt_title)
        val txtDesc: TextView = findViewById(R.id.movie_txt_desc)
        val txtCast: TextView = findViewById(R.id.movie_txt_cast)
        val rv : RecyclerView = findViewById(R.id.movie_rv_similar)

        val id = intent?.getIntExtra("id", 0) ?: throw IllegalStateException("Id não foi encontrado")

        val url = "https://api.tiagoaguiar.co/netflixapp/movie/$id?apiKey=9207fe2f-8803-4efc-8fbf-9e643ef62888"

        MovieTask(this).execute(url)

        txtTitle.text = "Batman Begins"
        txtDesc.text = "Essa é uma descrição do filme"
        txtCast.text = getString(R.string.cast, "Ator A, Ator B, Atriz A, Atriz B")


        val movies = mutableListOf<Movie>()

        rv.layoutManager = GridLayoutManager(this, 3)
        rv.adapter = MovieAdapter(movies, R.layout.movie_item_similar)

        val toolbar: Toolbar = findViewById(R.id.movie_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null

        // Busquei o desenhavel (layer-list)
        val layerDrawable : LayerDrawable = ContextCompat.getDrawable(this, R.drawable.shadows) as LayerDrawable

        //busquei o filme que eu quero
        val movieCover = ContextCompat.getDrawable(this, R.drawable.movie_4)

        //atrabui a esse layer-list o novo filme
        layerDrawable.setDrawableByLayerId(R.id.cover_drawable, movieCover)

        //set no imageView
        val coverImg: ImageView = findViewById(R.id.movie_img)
        coverImg.setImageDrawable(layerDrawable)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPreExecute() {

    }

    override fun onResult(movieDetail: MovieDetail) {
        Log.i("teste", movieDetail.toString())
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}