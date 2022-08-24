package com.example.praticamvp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.praticamvp.R
import com.example.praticamvp.databinding.ItemNewsBinding
import com.example.praticamvp.model.Article

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    inner class ViewHolder (val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = differ.currentList[position]
        with(holder){
            Glide.with(binding.ivNews)
                .load(article.urlToImage)
                .into(binding.ivNews)
            binding.tvTitle.text = article.source?.name
            binding.tvDescription.text = article.description
            binding.clArticle.setOnClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }

    private var onItemClickListener : ((Article)->Unit)? = null

    fun setOnClickListner(listner : (Article)-> Unit){
        onItemClickListener = listner
    }
}