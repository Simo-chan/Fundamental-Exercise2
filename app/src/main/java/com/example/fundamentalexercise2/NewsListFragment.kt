package com.example.fundamentalexercise2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView


class NewsListFragment : Fragment(R.layout.fragment_news_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newsRecyclerAdapter = NewsRecyclerAdapter()
        newsRecyclerAdapter.newsDataSet = DataUtils().newsList

        newsRecyclerAdapter.onItemClick =
            { newsItem: NewsItem ->
                val fragment = NewsDetailsFragment.newInstance(newsItem)
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit()
            }

        val recyclerView: RecyclerView = view.findViewById(R.id.newsRecyclerView)
        recyclerView.addItemDecoration(CustomItemDecoration(4))
        recyclerView.adapter = newsRecyclerAdapter

    }

}


