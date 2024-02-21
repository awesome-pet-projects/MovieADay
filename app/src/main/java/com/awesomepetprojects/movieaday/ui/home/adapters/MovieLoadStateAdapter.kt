package com.awesomepetprojects.movieaday.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.awesomepetprojects.movieaday.databinding.ItemLoadStateBinding

class MovieLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<MovieLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding =
            ItemLoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(binding)
    }

    inner class LoadStateViewHolder(private val binding: ItemLoadStateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            setContent(loadState)
            setListeners()
        }

        private fun setListeners() {
            binding.apply {
                loadStateRetry.setOnClickListener {
                    retry.invoke()
                }
            }
        }

        private fun setContent(loadState: LoadState) {
            binding.apply {
                loadStateRetry.isVisible = loadState is LoadState.Error
                textLoadStateError.isVisible = loadState is LoadState.Error
                loadStateProgress.isVisible = loadState is LoadState.Loading

                if (loadState is LoadState.Error) {
                    textLoadStateError.text = loadState.error.localizedMessage
                }
            }
        }
    }

}