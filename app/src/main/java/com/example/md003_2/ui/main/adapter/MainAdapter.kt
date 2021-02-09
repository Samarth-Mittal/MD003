package com.example.md003_2.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.md003_2.R
import com.example.md003_2.data.model.Movie
import kotlinx.android.synthetic.main.movie_row.view.*

class MainAdapter(private val movies: ArrayList<Movie>, var clickListener: OnMovieClickListener) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun initialize(movie: Movie, action: OnMovieClickListener){
            itemView.setOnClickListener{
                action.onItemClick(movie, adapterPosition)
            }
        }

        fun bind(movie: Movie) {
            itemView.apply {
                textViewMovieTitle.text = movie.title
                Glide.with(imageViewMoviePoster.context)
                    .load(movie.poster)
                    .into(imageViewMoviePoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_row, parent, false))

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(movies[position])
        holder.initialize(movies.get(position), clickListener)
    }

    fun addMovies(movies: Movie) {
        this.movies.apply {
            add(movies)
        }

    }
}

interface OnMovieClickListener{
    fun onItemClick(movie: Movie, position: Int)
}