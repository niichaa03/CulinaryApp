package com.niichaa.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.niichaa.core.databinding.ItemListCulinaryBinding
import com.niichaa.core.domain.model.Culinary

class CulinaryAdapter(
    private var listCulinary: List<Culinary>
) : RecyclerView.Adapter<CulinaryAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemListCulinaryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemListCulinaryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val culinary = listCulinary[position]
        holder.apply {
            Glide.with(itemView.context)
                .load(culinary.image)
                .into(binding.imgItemCulinary)
            binding.tvItemTitle.text = culinary.title

            binding.cvCulinary.setOnClickListener {
                onItemClickCallback.onItemClicked(listCulinary[holder.adapterPosition])
            }
        }

    }

    override fun getItemCount(): Int {
        return listCulinary.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(culinary: Culinary)
    }
}


