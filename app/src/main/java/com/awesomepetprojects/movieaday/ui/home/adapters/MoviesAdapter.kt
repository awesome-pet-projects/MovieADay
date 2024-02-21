package com.awesomepetprojects.movieaday.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.awesomepetprojects.movieaday.data.models.Movie
import com.awesomepetprojects.movieaday.databinding.ItemMovieBinding

class MoviesAdapter(
    private val callback: (Movie) -> Unit,
) : PagingDataAdapter<Movie, MoviesAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    inner class MovieViewHolder(private var binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            setContent(movie)
            setListeners(movie)
        }

        private fun setListeners(movie: Movie) {
            binding.apply {
                root.setOnClickListener {
                    if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                        callback.invoke(movie)
                    }
                }
            }
        }

        private fun setContent(movie: Movie) {
            binding.apply {
                textMovieTitle.text = movie.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let {
            holder.bind(it)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
                oldItem == newItem
        }
    }
}