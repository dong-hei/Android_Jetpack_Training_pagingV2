package com.dk.pagingv2.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dk.pagingv2.R
import com.dk.pagingv2.data.GithubResponse

class MyAdapter : PagingDataAdapter<GithubResponse.GithubResponseItem, MyAdapter.MyViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val  DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<GithubResponse.GithubResponseItem>() {
                override fun areItemsTheSame(
                    oldItem: GithubResponse.GithubResponseItem,
                    newItem: GithubResponse.GithubResponseItem
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: GithubResponse.GithubResponseItem,
                    newItem: GithubResponse.GithubResponseItem
                ): Boolean {
                   return oldItem == newItem
                }

            }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textArea = view.findViewById<TextView>(R.id.textArea)

        fun bind(item : GithubResponse.GithubResponseItem){
            textArea.text = item.name
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return MyViewHolder(view)
    }


}