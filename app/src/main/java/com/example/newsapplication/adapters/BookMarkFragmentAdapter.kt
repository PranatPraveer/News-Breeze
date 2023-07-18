package com.example.newsapplication.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapplication.BookMarkFragment
import com.example.newsapplication.R
import com.example.newsapplication.databinding.NewsItem2Binding
import com.example.newsapplication.models.Article

class BookMarkFragmentAdapter (
    val context: BookMarkFragment,
    private val onBookMarkDelete: (Article) -> Unit
    ):
        ListAdapter<Article, BookMarkFragmentAdapter.NewsViewHolder>(com.example.newsapplication.adapters.MainFragmentAdapter.ComparatorDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookMarkFragmentAdapter.NewsViewHolder {
        val binding = NewsItem2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: BookMarkFragmentAdapter.NewsViewHolder, position: Int) {
        val Article = getItem(position)
        Article?.let {
            holder.bind(it)
        }
    }

    inner class NewsViewHolder(private val binding: NewsItem2Binding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.tvTitle.text = article.title
            binding.tvDesc.text = article.description
            Glide.with(context).load(article.urlToImage).placeholder(R.drawable.ic_imageload)
                .into(binding.newsImage)

            binding.btnDelete.setOnClickListener {
                onBookMarkDelete(article)
            }
            binding.BtnRead.setOnClickListener {
                val bundle= Bundle()
                bundle.putString("Image",article.urlToImage)
                bundle.putString("Title", article.title)
                bundle.putString("News",article.url)
                Navigation.findNavController(it).navigate(R.id.action_bookMarkFragment_to_readFragment,bundle)
            }
        }
    }
class ComparatorDiffUtil : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id== newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}
}
