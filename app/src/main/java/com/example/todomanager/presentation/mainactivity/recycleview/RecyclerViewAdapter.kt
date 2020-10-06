package com.example.todomanager.presentation.mainactivity.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todomanager.R
import com.example.todomanager.domain.model.Task

/**
 * MainActivityのRecycleViewのViewAdapter。
 * データと表示の間の変換を行うアダプター。
 * 参考：https://qiita.com/naoi/items/f8a19d6278147e98bbc2
 */
open class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewHolder> {

    private var list: List<Task>

    /**
     * コンストラクタでデータリストを指定する。
     */
    constructor(dataList: List<Task>) {
        this.list = dataList
    }

    /**
     * レイアウトファイルからViewHolderを生成する。
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.sample_row_layout, parent, false);
        return RecyclerViewHolder(inflate)
    }

    /**
     * データリストの最大値を取得する。
     */
    override fun getItemCount(): Int {
        return list.size
    }

    /**
     * [position]で指定した位置のデータを、ViewHolderにバインドする。
     */
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.titleView.text = list[position].title
        holder.detailView.text = list[position].detail
    }

}