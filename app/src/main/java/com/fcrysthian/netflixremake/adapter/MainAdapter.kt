package com.fcrysthian.netflixremake.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fcrysthian.netflixremake.R
import com.fcrysthian.netflixremake.model.Movie

class MainAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MainAdapter.MovieViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)

    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MovieViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(movie: Movie) {
            val imageCover : ImageView = itemView.findViewById(R.id.img_cover)
            imageCover.setImageResource(movie.coverUrl)
        }

    }

}