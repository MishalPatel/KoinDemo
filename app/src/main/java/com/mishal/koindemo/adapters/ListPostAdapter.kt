package com.mishal.koindemo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mishal.koindemo.R
import com.mishal.koindemo.data.model.User
import com.mishal.koindemo.databinding.RowItemBinding

class ListPostAdapter : RecyclerView.Adapter<ListPostAdapter.ListViewHolder>() {
    private var listPost = ArrayList<User>()
    private var customClickListener: CustomClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return ListViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return listPost.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//         binding.setVariable(BR.strForks, listData[p1].forks.toString())
//        binding.setVariable(BR.strStars, listData[p1].stars.toString())
//        binding.setVariable(BR.strRepoName, listData[p1].name)
//        binding.setVariable(BR.strDescription, listData[p1].description)
        holder.binding?.tvRepoName?.text = listPost[position].original_title
        holder.binding?.tvRepoDescription?.text = listPost[position].overview

        holder.binding?.llMain?.setOnClickListener {
            customClickListener!!.onClick(it, position)
        }
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = DataBindingUtil.bind<RowItemBinding>(itemView)
    }

    fun addPostData(listPost: ArrayList<User>) {
        this.listPost.addAll(listPost)
        notifyDataSetChanged()
    }

    fun setCustomClickListener(customClickListener: CustomClickListener) {
        this.customClickListener = customClickListener
    }

    interface CustomClickListener {
        fun onClick(view: View, position: Int)
    }
}