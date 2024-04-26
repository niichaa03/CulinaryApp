package com.niichaa.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.niichaa.core.R
import com.niichaa.core.databinding.ItemListCulinaryBinding
import com.niichaa.core.domain.model.Culinary

class CulinaryAdapter: RecyclerView.Adapter<CulinaryAdapter.ListViewHolder>() {

    private var listCulinary = ArrayList<Culinary>()
    var onItemClick: ((Culinary) -> Unit)? = null

    fun setCulinary(newListCulinary: List<Culinary>?){
        if (newListCulinary == null) return
        listCulinary.clear()
        listCulinary.addAll(newListCulinary)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_culinary, parent, false))

    override fun getItemCount(): Int = listCulinary.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val culinary = listCulinary[position]
        holder.bind(culinary)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListCulinaryBinding.bind(itemView)
        fun bind(culinary: Culinary){
            with(binding){
                Glide.with(itemView.context)
                    .load(culinary.image)
                    .into(imgItemCulinary)
                tvItemTitle.text = culinary.title
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listCulinary[adapterPosition])
            }
        }
    }
}