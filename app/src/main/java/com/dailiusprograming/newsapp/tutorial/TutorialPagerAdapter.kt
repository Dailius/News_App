package com.dailiusprograming.newsapp.tutorial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.tutorial.model.TutorialPage

class TutorialPagerAdapter(
    private val tutorialPages: List<TutorialPage>
) : RecyclerView.Adapter<TutorialPagerAdapter.TutorialPagerViewHolder>() {
    class TutorialPagerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image = view.findViewById<ImageView>(R.id.tutorialImageView)
        private val description = view.findViewById<TextView>(R.id.tutorialTextView)
        fun bin(tutorialPage: TutorialPage) {
            image.setImageResource(tutorialPage.image)
            description.text = tutorialPage.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TutorialPagerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.tutorial_page,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: TutorialPagerViewHolder, position: Int) {
        holder.bin(tutorialPages[position])
    }

    override fun getItemCount(): Int = tutorialPages.size

}