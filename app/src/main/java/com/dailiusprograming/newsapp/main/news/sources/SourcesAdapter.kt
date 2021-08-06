package com.dailiusprograming.newsapp.main.news.sources


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dailiusprograming.newsapp.databinding.FragmentSourcesListItemBinding
import com.dailiusprograming.newsapp.main.news.sources.data.model.SourceDomain

class SourcesAdapter(private val onClick: (SourceDomain) -> Unit) :
    ListAdapter<SourceDomain, SourcesAdapter.SourceViewHolder>(ComparatorLists()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = SourceViewHolder(
        FragmentSourcesListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        if (getItem(position) != null) {
            holder.bind(getItem(position))
        }
    }

    inner class SourceViewHolder(
        private val binding: FragmentSourcesListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private var currentSourceList: SourceDomain? = null

        init {
            binding.root.setOnClickListener { currentSourceList?.let(onClick) }
        }

        fun bind(sourceDomain: SourceDomain) {
            currentSourceList = sourceDomain
            binding.titleTextView.text = sourceDomain.name
            binding.descriptionTextView.text = sourceDomain.description
        }
    }

    class ComparatorLists : DiffUtil.ItemCallback<SourceDomain>() {
        override fun areItemsTheSame(currentItem: SourceDomain, newItem: SourceDomain) =
            currentItem.id == newItem.id

        override fun areContentsTheSame(currentItem: SourceDomain, newItem: SourceDomain) =
            currentItem == newItem
    }
}
