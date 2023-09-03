package com.example.fundamentalexercise2

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.fundamentalexercise2.networking.models.NewsViewModel
import com.example.fundamentalexercise2.recyclerview.CustomItemDecoration
import com.example.fundamentalexercise2.recyclerview.NewsRecyclerAdapter
import com.example.fundamentalexercise2.recyclerview.NewsItem

const val TAG = "NewsListFragment"

class NewsListFragment : Fragment(R.layout.fragment_news_list) {
    private var newsViewModel: NewsViewModel? = null
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "News are shown in the thread ${Thread.currentThread().name}")
        progressBar = view.findViewById(R.id.circleProgressBar)

        val newsRecyclerAdapter = NewsRecyclerAdapter()
        val recyclerView: RecyclerView = view.findViewById(R.id.newsRecyclerView)
        recyclerView.addItemDecoration(CustomItemDecoration(4))
        recyclerView.adapter = newsRecyclerAdapter


        newsViewModel?.newsLiveData?.observe(viewLifecycleOwner) { newsData ->
            Log.d(TAG, "News data changed")
            progressBar?.visibility = View.GONE

            newsRecyclerAdapter.updateNewsDataset(newsData)
        }

        newsViewModel?.loadNews()

        newsRecyclerAdapter.onItemClick =
            { newsItem: NewsItem ->
                val fragment = NewsDetailsFragment.newInstance(newsItem)
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit()
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        progressBar = null
    }
}


