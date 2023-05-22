package com.example.fundamentalexercise2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class NewsListFragment : Fragment() {

    private var orientationEventListener: OrientationEventListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newsRecyclerAdapter = NewsRecyclerAdapter()
        newsRecyclerAdapter.newsDataSet = DataUtils().newsList

        newsRecyclerAdapter.onItemClick =
            { _ ->
                TODO()//Don't know how to retrieve data from newsList to pass data to NewsDetailsFragment
            }

        val recyclerView: RecyclerView = view.findViewById(R.id.newsRecyclerView)
        recyclerView.addItemDecoration(CustomItemDecoration(4))
        recyclerView.adapter = newsRecyclerAdapter

        val layoutManager = GridLayoutManager(context, 1)
        recyclerView.layoutManager = layoutManager
        orientationEventListener =
            object : OrientationEventListener(context) {
                override fun onOrientationChanged(orientation: Int) {
                    if (orientation == 90 || orientation == 270) {
                        layoutManager.spanCount = 2
                    } else {
                        layoutManager.spanCount = 1
                    }
                }
            }
        orientationEventListener?.enable()
    }

    override fun onDestroy() {
        super.onDestroy()
        orientationEventListener?.disable()
    }

    companion object {
        private const val ARG_IMAGE = "image"
        private const val ARG_TITLE = "title"
        private const val ARG_DATE = "date"
        private const val ARG_NEWS_TEXT = "news text"

        fun newInstance(
            image: String,
            title: String,
            date: String,
            newsText: String
        ): NewsListFragment {
            val fragment = NewsListFragment()
            val bundle = Bundle().apply {
                putString(ARG_IMAGE, image)
                putString(ARG_TITLE, title)
                putString(ARG_DATE, date)
                putString(ARG_NEWS_TEXT, newsText)
            }
            fragment.arguments = bundle
            return fragment
        }
    }
}