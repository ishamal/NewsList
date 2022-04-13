package com.example.myapplication.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.service.response.Article
import com.example.myapplication.ui.viewholders.TopNewsViewHolder

class TopNewsAdapter(private val mList: List<Article>, var listener: ArticleClickListener )
    : RecyclerView.Adapter<TopNewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopNewsViewHolder {
        return  TopNewsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TopNewsViewHolder, position: Int) {
        holder.onBind(mList[position],listener)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}