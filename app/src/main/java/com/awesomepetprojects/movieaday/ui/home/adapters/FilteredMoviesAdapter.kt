package com.awesomepetprojects.movieaday.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.awesomepetprojects.movieaday.data.models.Movie
import com.awesomepetprojects.movieaday.databinding.ItemMovieBinding

class FilteredMoviesAdapter : ListAdapter<Movie, FilteredMoviesAdapter.FilteredMovieViewHolder>(
    DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilteredMovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilteredMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilteredMovieViewHolder, position: Int) {
        val currentMovie = getItem(position)
        holder.bind(currentMovie)
    }

    inner class FilteredMovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            setContent(movie)
        }

        private fun setContent(movie: Movie) {
            binding.apply {
                textMovieTitle.text = movie.title
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
                oldItem == newItem
        }
    }
}