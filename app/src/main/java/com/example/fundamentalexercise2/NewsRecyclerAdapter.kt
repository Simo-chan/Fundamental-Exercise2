package com.example.fundamentalexercise2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class NewsRecyclerAdapter : RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>() {

    var onItemClick: ((NewsItem) -> Unit)? = null
    var newsDataSet = emptyList<NewsItem>()
        set(value) {
            val newsDiffUtil = NewsDiffUtilCallBack(field, value)
            val diffResult = DiffUtil.calculateDiff(newsDiffUtil)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.items_of_recyclerview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = newsDataSet.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newsDataSet[position])
        val urlData = newsDataSet[position].imageURL
        Glide.with(holder.headingImage)
            .load(urlData)
            .into(holder.headingImage)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val headingImage: ImageView = itemView.findViewById(R.id.headingImage)
        private val category: TextView = itemView.findViewById(R.id.categoryText)
        private val title: TextView = itemView.findViewById(R.id.titleText)
        private val previewText: TextView = itemView.findViewById(R.id.previewText)
        private val date: TextView = itemView.findViewById(R.id.publishDate)

        fun bind(news: NewsItem) {
            category.text = news.category.name
            title.text = news.title
            previewText.text = news.previewText
            date.text = news.publishDate.toString()
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(newsDataSet[absoluteAdapterPosition])
            }
        }

    }
}