package com.example.newsapplication.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapplication.MainFragment
import com.example.newsapplication.R
import com.example.newsapplication.databinding.NewsItemBinding
import com.example.newsapplication.models.Article

class MainFragmentAdapter(
    val context: MainFragment,
    private val onBookMarkClicked: (Article) -> Unit,
    private val onBookMarkDelete: (Article) -> Unit
):
    ListAdapter<Article,MainFragmentAdapter.NewsViewHolder>(ComparatorDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainFragmentAdapter.NewsViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainFragmentAdapter.NewsViewHolder, position: Int) {
        val Article = getItem(position)
        Article?.let {
            holder.bind(it)
        }
    }

    inner class NewsViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.tvTitle.text = article.title
            binding.tvDesc.text = article.description
            binding.tvTime.text=article.publishedAt.substring(0,10)
            Glide.with(context).load(article.urlToImage).placeholder(R.drawable.ic_imageload)
                .into(binding.newsImage)

            binding.btnFavourite.setOnClickListener {
                onBookMarkClicked(article)
               // binding.favouriteimage.setImageDrawable(ContextCompat.getDrawable(binding.favouriteimage.context,R.drawable.ic_clicked_star))
            }
           // binding.favouriteimage.setOnClickListener {
             //   onBookMarkDelete(article.id)
              //  binding.favouriteimage.setImageDrawable(ContextCompat.getDrawable(binding.favouriteimage.context,R.drawable.ic_star))
            //}
              binding.root.setOnClickListener {
                //  onNoteClicked(note)

            }
            binding.BtnRead.setOnClickListener {
                val bundle= Bundle()
                bundle.putString("SourceName",article.source.name)
                bundle.putString("SourceId",article.source.id)
                bundle.putInt("Id",article.id)
                bundle.putString("Author",article.author)
                bundle.putString("Description",article.description)
                bundle.putString("PublishedAt",article.publishedAt)
                bundle.putString("Content",article.content)
                bundle.putString("Image",article.urlToImage)
                bundle.putString("Title", article.title)
                bundle.putString("News",article.url)
                Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_readFragment,bundle)
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