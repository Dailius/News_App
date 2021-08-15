package com.dailiusprograming.newsapp.main.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.dailiusprograming.newsapp.databinding.FragmentArticlesListItemBinding
import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleDomain

class FavoritesAdapter(
    private val onClick: (ArticleDomain) -> Unit
) : ListAdapter<ArticleDomain, FavoritesViewHolder>(
    FavoritesListArticleComparator()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FavoritesViewHolder(
        FragmentArticlesListItemBinding.inflate
            (LayoutInflater.from(parent.context), parent, false), onClick
    )

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class FavoritesListArticleComparator : DiffUtil.ItemCallback<ArticleDomain>() {
        override fun areItemsTheSame(oldItem: ArticleDomain, newItem: ArticleDomain) =
            oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: ArticleDomain, newItem: ArticleDomain) =
            oldItem == newItem
    }
}
