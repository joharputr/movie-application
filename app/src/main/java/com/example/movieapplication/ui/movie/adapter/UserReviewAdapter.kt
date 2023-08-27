package com.example.movieapplication.ui.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapplication.databinding.ItemUserReviewBinding
import com.example.movieapplication.ui.movie.model.UserReviewResultModel

class UserReviewAdapter(
) :
    PagingDataAdapter<UserReviewResultModel, UserReviewViewHolder>(UserReviewDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserReviewViewHolder {
        return UserReviewViewHolder(
            ItemUserReviewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: UserReviewViewHolder, position: Int) {
        holder.bind(getItem(position)   )
    }
}

class UserReviewDiffCallBack : DiffUtil.ItemCallback<UserReviewResultModel>() {
    override fun areItemsTheSame(
        oldItem: UserReviewResultModel,
        newItem: UserReviewResultModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: UserReviewResultModel,
        newItem: UserReviewResultModel
    ): Boolean {
        return oldItem == newItem
    }
}

class UserReviewViewHolder(
    val binding: ItemUserReviewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        data: UserReviewResultModel?,
    ) {
        data?.let {
            binding.textAuthor.text = data.author
            binding.textContent.text = data.content
        }
    }

}