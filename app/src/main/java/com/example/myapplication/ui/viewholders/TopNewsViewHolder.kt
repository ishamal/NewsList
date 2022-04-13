package com.example.myapplication.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemTopNewsBinding
import com.example.myapplication.service.response.Article
import com.example.myapplication.ui.adapters.ArticleClickListener

class TopNewsViewHolder(val binding: ItemTopNewsBinding): RecyclerView.ViewHolder(binding.root) {


    companion object {

        fun create(parent: ViewGroup): TopNewsViewHolder {
            val binding = ItemTopNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return TopNewsViewHolder(binding)
        }
    }

    fun onBind(articles: Article,  listener: ArticleClickListener) {

        Glide.with(binding.root.context)
            .load(articles.urlToImage)
            .centerCrop()
            .into(binding.appCompatImageView)

        binding.authorName.text = "By ${articles.author}"
        binding.titleText.text = articles.title
        binding.abstractText.text = articles.description

        binding.button.setOnClickListener {
            listener.onArticleClicked(articles)
        }

    }

}