package com.example.movieapplication.ui.genre.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplication.databinding.ItemGenreBinding
import com.example.movieapplication.ui.genre.model.Genres

class GenreAdapter(private val listenerClick: (data: Genres) -> Unit) :
    RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    private var genres: ArrayList<Genres> = ArrayList<Genres>()

    inner class GenreViewHolder(binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val textNama = binding.genreText
        val cardMovie = binding.cardMovie
    }

    fun setItemList(genres: ArrayList<Genres>) {
        this.genres = genres
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val binding = ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(binding)
    }

    override fun getItemCount() = genres.size

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        with(holder) {
            with(genres[position]) {
                textNama.text = name
                holder.cardMovie.setOnClickListener {
                    listenerClick.invoke(this)
                }
            }

        }


    }

}