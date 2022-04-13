package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDashbordBinding
import com.example.myapplication.service.response.Article
import com.example.myapplication.ui.adapters.ArticleClickListener
import com.example.myapplication.ui.adapters.HeadlineAdapter
import com.example.myapplication.ui.adapters.TopNewsAdapter
import com.example.myapplication.util.GsonUtils
import com.example.myapplication.viewModel.DashBordViewModel
import com.google.android.material.chip.Chip
import java.lang.Exception

class NewsFeedFragment : Fragment(), ArticleClickListener {

    private lateinit var binding: FragmentDashbordBinding
    private val viewModel: DashBordViewModel by viewModels()
    private var headlineAdapter : HeadlineAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentDashbordBinding.inflate(inflater)
        initObserver()
        initRecycleView(emptyList())
        initHeadlineRecycleView()
        initClickSearch()
        viewModel.getTopNewsList()
        viewModel.getHeadLineList("health")
        notifyIsChecked(binding.chipHealth)

        return binding.root
    }

    fun notifyIsChecked(chip : Chip) {
        chip.isChecked = true
    }

    private fun initObserver() {

        viewModel.headlineResponse.observe(viewLifecycleOwner) {
            if (!it?.articles.isNullOrEmpty()) {
                it?.articles?.let { it1 -> headlineAdapter?.updateData(it1) }
            }
        }

        viewModel.topNewsResponse.observe(viewLifecycleOwner) {
            if (!it?.articles.isNullOrEmpty()) {
                it?.articles?.let { it1 -> initRecycleView(it1) }
            }
        }

        viewModel.error.observe(viewLifecycleOwner){

        }

        viewModel.inProgress.observe(viewLifecycleOwner){

        }
    }

    fun initRecycleView(articles: List<Article>) {
        binding.latestNewsList.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
            adapter = TopNewsAdapter(articles,this@NewsFeedFragment)
        }
    }

    fun initHeadlineRecycleView() {
        headlineAdapter = HeadlineAdapter(emptyList(),this)
        binding.topNewsList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = headlineAdapter
        }
    }

    fun initClickSearch() {
        binding.chipHealth.setOnClickListener {
            viewModel.getHeadLineList("health")
        }

        binding.chipTechnology.setOnClickListener {
            viewModel.getHeadLineList("technology")
        }

        binding.chipBusiness.setOnClickListener {
            viewModel.getHeadLineList("business")
        }

        binding.chipEntertainment.setOnClickListener {
            viewModel.getHeadLineList("entertainment")
        }

        binding.chipGeneral.setOnClickListener {
            viewModel.getHeadLineList("general")
        }

        binding.chipScience.setOnClickListener {
            viewModel.getHeadLineList("science")
        }

        binding.chipSport.setOnClickListener {
            viewModel.getHeadLineList("sports")
        }

    }

    override fun onArticleClicked(article: Article?) {
        try {
            findNavController().navigate(R.id.action_newsFeedFragment_to_detailFragment
                , bundleOf("articleData" to GsonUtils.gsonParser?.toJson(article)))
        } catch (e : Exception) {

        }
    }
}