package com.example.todomanager.presentation.mainactivity.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todomanager.R
import com.example.todomanager.domain.model.Task

open class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewHolder> {

    private var list: List<Task>

    constructor(dataList: List<Task>) {
        this.list = dataList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.sample_row_layout, parent, false);
        return RecyclerViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.titleView.text = list[position].title
        holder.detailView.text = list[position].detail
    }

}