package com.example.myapplication.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.service.response.Article
import com.example.myapplication.ui.viewholders.HeadLineViewHolder

class HeadlineAdapter(private var mList: List<Article>, var listener: ArticleClickListener)
    : RecyclerView.Adapter<HeadLineViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadLineViewHolder {
        return  HeadLineViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HeadLineViewHolder, position: Int) {
        holder.onBind(mList[position],listener)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun updateData(updateList: List<Article>) {
        mList = updateList
        notifyDataSetChanged()
    }

}

interface ArticleClickListener {
    fun onArticleClicked(article : Article?)
}