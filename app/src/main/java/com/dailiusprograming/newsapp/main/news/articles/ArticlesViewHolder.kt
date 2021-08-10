package com.dailiusprograming.newsapp.main.news.articles

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.ViewSizeResolver
import com.dailiusprograming.newsapp.databinding.FragmentArticlesListItemBinding
import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleDomain

class ArticlesViewHolder(
    private val binding: FragmentArticlesListItemBinding,
    private val onClick: (ArticleDomain) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private var currentArticleList: ArticleDomain? = null

    init {
        binding.root.setOnClickListener {
            currentArticleList?.let { result -> onClick(result) }
        }
    }

    fun bind(list: ArticleDomain) {
        currentArticleList = list
        binding.apply {
            titleTextView.text = list.title
            dateTextView.text = list.publishedAt.toString()
            descriptionTextView.text = list.description
            favoriteCheckBox.isChecked = list.favorites
            val photoPath = list.urlToImage
            articleIImageView.load(photoPath) {
                crossfade(enable = true)
                crossfade(durationMillis = 500)
                size(ViewSizeResolver(articleIImageView))
            }
        }
    }
}
