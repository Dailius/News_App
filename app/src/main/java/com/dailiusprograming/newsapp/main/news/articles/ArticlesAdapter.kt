package com.dailiusprograming.newsapp.main.news.articles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.dailiusprograming.newsapp.databinding.FragmentArticlesListItemBinding
import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleDomain

class ArticlesAdapter(
    private val onClick: (ArticleDomain) -> Unit
) : ListAdapter<ArticleDomain, ArticlesViewHolder>(
    Comparator()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ArticlesViewHolder(
        FragmentArticlesListItemBinding.inflate
            (LayoutInflater.from(parent.context), parent, false), onClick
    )

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class Comparator : DiffUtil.ItemCallback<ArticleDomain>() {
        override fun areItemsTheSame(oldItem: ArticleDomain, newItem: ArticleDomain) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: ArticleDomain, newItem: ArticleDomain) =
            oldItem == newItem
    }
}
