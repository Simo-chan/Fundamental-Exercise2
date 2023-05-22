package com.example.fundamentalexercise2

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CustomItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val spacingPx = spacing * view.context.resources.displayMetrics.density.toInt()
        outRect.bottom = spacingPx
    }
}