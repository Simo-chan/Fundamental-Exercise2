package com.example.fundamentalexercise2

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.fundamentalexercise2.recyclerview.NewsItem

@Suppress("DEPRECATION")
class NewsDetailsFragment : Fragment(R.layout.fragment_news_details) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataReceived = arguments?.getSerializable(ARG_DATA) as NewsItem

        dataReceived.let {
            val imageView = view.findViewById<ImageView>(R.id.newsImage)
            Glide.with(this)
                .load(it.imageURL)
                .into(imageView)
            view.findViewById<TextView>(R.id.heading).text = it.title
            view.findViewById<TextView>(R.id.date).text = it.publishDate.toString()
            view.findViewById<TextView>(R.id.newsText).text = it.fullText
        }
        val toolbar: Toolbar = view.findViewById(R.id.toolBar)
        toolbar.title = dataReceived.category
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

    }

    companion object {
        private const val ARG_DATA = "data"

        fun newInstance(
            item: NewsItem
        ): NewsDetailsFragment {
            val fragment = NewsDetailsFragment()
            val arguments = bundleOf(
                ARG_DATA to item
            )
            fragment.arguments = arguments
            return fragment
        }
    }

}