package com.example.madlevel6task2.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.madlevel6task2.R
import com.example.madlevel6task2.databinding.ItemMovieBinding
import com.example.madlevel6task2.model.MovieInfo

class MovieAdapter(private val movies: List<MovieInfo>, private val onClick: (MovieInfo) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(movies[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMovieBinding.bind(itemView)

        init {
            itemView.setOnClickListener { onClick(movies[adapterPosition]) }
        }

        fun bind(movie: MovieInfo) {
            binding.tvRanking.text = String.format("%d.", movies.indexOf(movie) + 1)
            val baseImageUrl = "https://image.tmdb.org/t/p/w500/"
            Glide.with(context).load(baseImageUrl + movie.posterPath).into(binding.ivMovieBg)
        }
    }
}