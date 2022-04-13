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
import com.example.myapplication.databinding.FragmentSearchBinding
import com.example.myapplication.service.response.Article
import com.example.myapplication.ui.adapters.ArticleClickListener
import com.example.myapplication.ui.adapters.HeadlineAdapter
import com.example.myapplication.ui.dialog.FilterClickListener
import com.example.myapplication.ui.dialog.SortDialog
import com.example.myapplication.util.GsonUtils
import com.example.myapplication.viewModel.DashBordViewModel
import java.lang.Exception

class SearchFragment : Fragment() , FilterClickListener, ArticleClickListener {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: DashBordViewModel by viewModels()
    private var headlineAdapter : HeadlineAdapter? = null
    private var searchText : String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBinding.inflate(inflater)
        initObserver()
        initHeadlineRecycleView()

        searchText = requireArguments().getString("Search")

        if (searchText != null){
            viewModel.getHeadLineListBySearch(searchText!!)
        }


        binding.chipFilter.setOnClickListener {
            var sortDialog = SortDialog(this)
            activity?.let { it1 -> sortDialog.show(it1?.supportFragmentManager, "sort") }
        }

        return binding.root
    }

    private fun initObserver() {

        viewModel.headlineResponse.observe(viewLifecycleOwner) {
            if (!it?.articles.isNullOrEmpty()) {
                it?.articles?.let { it1 -> headlineAdapter?.updateData(it1) }
            }
        }

        viewModel.error.observe(viewLifecycleOwner){

        }

        viewModel.inProgress.observe(viewLifecycleOwner){

        }
    }


    fun initHeadlineRecycleView() {
        headlineAdapter = HeadlineAdapter(emptyList(),this)
        binding.topNewsList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = headlineAdapter
        }
    }

    override fun onSearchClicked(filter: String?) {
        if (!filter.isNullOrEmpty()) {
            viewModel.getHeadLineListBySearch(searchText!!, filter)
        } else {
            viewModel.getHeadLineListBySearch(searchText!!)
        }
    }

    override fun onArticleClicked(article: Article?) {
        try {
            findNavController().navigate(
                R.id.action_searchFragment_to_detailFragment
                , bundleOf("articleData" to GsonUtils.gsonParser?.toJson(article))
            )
        } catch (e : Exception) {

        }
    }
}