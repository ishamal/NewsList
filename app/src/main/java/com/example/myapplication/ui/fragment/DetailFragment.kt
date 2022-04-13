package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.FragmentDetailsBinding
import com.example.myapplication.service.response.Article
import com.example.myapplication.util.GsonUtils

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    var article : Article? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater)

        var articleString = requireArguments().getString("articleData")
        article = GsonUtils.gsonParser!!.fromJson(articleString, Article::class.java)
        bindData()
        return binding.root
    }

    fun bindData() {
        Glide.with(binding.root.context)
            .load(article?.urlToImage)
            .centerCrop()
            .into(binding.imageView)

        binding.authorName.text = "By ${article?.author}"
        binding.titleText.text = article?.title
        binding.abstractText.text = article?.description
        binding.contentText.text = article?.content
    }

}