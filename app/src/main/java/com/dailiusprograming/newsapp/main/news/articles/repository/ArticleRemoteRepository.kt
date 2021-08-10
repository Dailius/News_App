package com.dailiusprograming.newsapp.main.news.articles.repository

import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleDomain
import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleItemResponse
import com.dailiusprograming.newsapp.main.news.articles.utils.ArticlesFilterType
import com.dailiusprograming.newsapp.main.news.articles.utils.DateUtil
import com.dailiusprograming.newsapp.utils.network.ApiService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ArticleRemoteRepository @Inject constructor(
    private val service: ApiService
) {
    fun getArticles(
        sourceId: String,
        articlesFilterType: ArticlesFilterType = ArticlesFilterType.DEFAULT,
    ): Single<List<ArticleDomain>> =
        service.getAllNews(
            apiServiceQueryMap = switchApiServiceQueryMap(articlesFilterType, sourceId)
        )
            .map { newsResponse -> newsResponse.articles.map(ArticleItemResponse::toDomain) }

    private fun switchApiServiceQueryMap(
        articlesFilterType: ArticlesFilterType,
        sourcesId: String
    ): Map<String, String> {
        val apiServiceQueryMap: Map<String, String>
        when (articlesFilterType) {
            ArticlesFilterType.TODAY -> {
                apiServiceQueryMap = mapOf(
                    SOURCES to sourcesId,
                    FROM_DATE to DateUtil.getFormattedDate(),
                    TO_DATE to DateUtil.getFormattedDate()
                )
            }
            ArticlesFilterType.NEWEST -> {
                apiServiceQueryMap = mapOf(
                    SOURCES to sourcesId,
                    FROM_DATE to DateUtil.getFormattedDate(),
                    TO_DATE to DateUtil.getFormattedDate(),
                    SORT_BY to SORT_BY_PUBLISHED_AT
                )
            }
            ArticlesFilterType.ALL_TIME ->
                apiServiceQueryMap = mapOf("sources" to sourcesId)
            ArticlesFilterType.DEFAULT ->
                apiServiceQueryMap = mapOf("sources" to sourcesId)
        }
        return apiServiceQueryMap
    }

    companion object {
        const val SOURCES = "sources"
        const val FROM_DATE = "fromDate"
        const val TO_DATE = "toDate"
        const val SORT_BY = "sortBy"
        const val SORT_BY_PUBLISHED_AT = "publishedAt"
    }
}
