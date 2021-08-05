package com.dailiusprograming.newsapp.utils.network

import com.dailiusprograming.newsapp.BuildConfig
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {
    @GET("sources")
    fun getSource(
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY
    ): Single<Any>

    @GET("everything")
    fun getAllNews(
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY,
        @QueryMap apiServiceQueryMap: Map<String, String>
    ): Single<Any>
}