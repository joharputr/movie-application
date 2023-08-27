package com.example.movieapplication.ui.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapplication.databinding.ItemMovieBinding
import com.example.movieapplication.ui.movie.model.DiscoverMovieResultModel

class MoviePagingAdapter(
    val ctx: Context,
    private val listenerCard: (resultModel: DiscoverMovieResultModel) -> Unit,
) :
    PagingDataAdapter<DiscoverMovieResultModel, DetailMovieViewHolder>(MovieDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailMovieViewHolder {
        return DetailMovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailMovieViewHolder, position: Int) {
        holder.bind(getItem(position), ctx, listenerCard)
    }
}

class MovieDiffCallBack : DiffUtil.ItemCallback<DiscoverMovieResultModel>() {
    override fun areItemsTheSame(
        oldItem: DiscoverMovieResultModel,
        newItem: DiscoverMovieResultModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: DiscoverMovieResultModel,
        newItem: DiscoverMovieResultModel
    ): Boolean {
        return oldItem == newItem
    }
}

class DetailMovieViewHolder(
    val binding: ItemMovieBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        data: DiscoverMovieResultModel?,
        ctx: Context,
        listenerCard: (resultModel: DiscoverMovieResultModel) -> Unit
    ) {
        data?.let {
            binding.textTitle.text = data.title
            Glide.with(ctx)
                .load("https://image.tmdb.org/t/p/original" + data.backdrop_path)
                .into(binding.imageView)

            binding.itemView.setOnClickListener {
                listenerCard.invoke(data)
            }
        }
    }

}