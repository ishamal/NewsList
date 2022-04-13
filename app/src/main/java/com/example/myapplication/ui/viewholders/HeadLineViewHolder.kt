package com.example.myapplication.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemHeadLineBinding
import com.example.myapplication.service.response.Article
import com.example.myapplication.ui.adapters.ArticleClickListener

class HeadLineViewHolder(val binding: ItemHeadLineBinding): RecyclerView.ViewHolder(binding.root) {

    companion object {

        fun create(parent: ViewGroup): HeadLineViewHolder {
            val binding = ItemHeadLineBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return HeadLineViewHolder(binding)
        }
    }

    fun onBind(articles: Article, listener: ArticleClickListener) {

        Glide.with(binding.root.context)
            .load(articles.urlToImage)
            .centerCrop()
            .placeholder(R.color.black)
            .into(binding.appCompatImageView)

        binding.authorText.text = articles.author
        binding.titleName.text = articles.title
        binding.timeText.text = articles.publishedAt
        binding.button.setOnClickListener {
            listener.onArticleClicked(articles)
        }

    }

}