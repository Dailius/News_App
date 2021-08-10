package com.dailiusprograming.newsapp.utils.network

import com.dailiusprograming.newsapp.BuildConfig
import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleResponse
import com.dailiusprograming.newsapp.main.news.sources.data.model.SourceResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {
    @GET("sources")
    fun getSource(
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY
    ): Single<SourceResponse>

    @GET("everything")
    fun getAllNews(
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY,
        @QueryMap apiServiceQueryMap: Map<String, String>
    ): Single<ArticleResponse>
}
