package com.example.fundamentalexercise2

import androidx.recyclerview.widget.DiffUtil

class NewsDiffUtilCallBack(
    private val oldList: List<NewsItem>,
    private val newList: List<NewsItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        newList[newItemPosition].id == oldList[oldItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        newList[newItemPosition] == oldList[oldItemPosition]

}