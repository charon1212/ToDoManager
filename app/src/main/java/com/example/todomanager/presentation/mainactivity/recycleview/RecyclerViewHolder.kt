package com.example.todomanager.presentation.mainactivity.recycleview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todomanager.R

/**
 * MainActivityのRecycleViewのViewHolder
 * 1行分のViewをホールドする役割を持つ。
 */
open class RecyclerViewHolder : RecyclerView.ViewHolder {

    var titleView: TextView
    var detailView: TextView

    constructor(itemView: View) : super(itemView) {
        titleView = itemView.findViewById<TextView>(R.id.title)
        detailView = itemView.findViewById<TextView>(R.id.detail)
    }

}