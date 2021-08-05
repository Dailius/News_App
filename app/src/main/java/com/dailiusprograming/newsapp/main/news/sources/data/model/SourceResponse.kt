package com.dailiusprograming.newsapp.main.news.sources.data.model

import com.google.gson.annotations.SerializedName

data class SourceResponse(
    val status: String,
    @SerializedName("sources")
    val sources: List<SourceItemResponse>
)
