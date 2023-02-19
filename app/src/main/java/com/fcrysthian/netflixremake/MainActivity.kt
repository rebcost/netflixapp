package com.fcrysthian.netflixremake

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fcrysthian.netflixremake.adapter.CategoryAdapter
import com.fcrysthian.netflixremake.model.Category
import com.fcrysthian.netflixremake.util.CategoryTask

class MainActivity : AppCompatActivity(), CategoryTask.Callback {

    private lateinit var progress: ProgressBar
    private val categories = mutableListOf<Category>()
    private lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progress = findViewById(R.id.progress_main)

        adapter = CategoryAdapter(categories) { id ->
            val intent = Intent(this@MainActivity, MovieActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }
        val rv: RecyclerView = findViewById(R.id.rv_main)
//        rv.layoutManager = LinearLayoutManager(this) Rolagem na vertical
        rv.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false) // rolagem na horizontal
        rv.adapter = adapter

        CategoryTask(this).execute("https://api.tiagoaguiar.co/netflixapp/home?apiKey=9207fe2f-8803-4efc-8fbf-9e643ef62888")
    }

    override fun onResult(categories: List<Category>) {
        //
        Log.i("Teste MainActivity", categories.toString())
        Toast.makeText(this, "Conexão estabelecida", Toast.LENGTH_SHORT).show()
        this.categories.clear()
        this.categories.addAll(categories)
        adapter.notifyDataSetChanged() // forca o adapter chamar de novo o onBindViewHolder
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