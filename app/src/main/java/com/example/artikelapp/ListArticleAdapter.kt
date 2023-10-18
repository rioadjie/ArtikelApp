package com.example.artikelapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artikelapp.databinding.ItemRowArticleBinding

class ListArticleAdapter(private val listArticle: ArrayList<Articles>) : RecyclerView.Adapter<ListArticleAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemRowArticleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowArticleBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listArticle.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (title, description, photo) = listArticle[position]
        holder.binding.imgItemPhoto.setImageResource(photo)
        holder.binding.tvItemTitle.text = title
        holder.binding.tvItemDescription.text = description

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listArticle[holder.adapterPosition]) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Articles)
    }
}