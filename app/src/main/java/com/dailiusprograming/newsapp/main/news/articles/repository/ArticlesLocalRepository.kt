package com.dailiusprograming.newsapp.main.news.articles.repository

import com.dailiusprograming.newsapp.main.news.articles.data.local.ArticleDao
import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleDomain
import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleEntity
import com.dailiusprograming.newsapp.main.news.articles.utils.ArticlesFilterType
import com.dailiusprograming.newsapp.main.news.articles.utils.DateUtil
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ArticlesLocalRepository @Inject constructor(
    private val articleDao: ArticleDao
) {
    private fun updateTodayNews(sourceId: String): Observable<List<ArticleDomain>> =
        articleDao.updateTodayNews(sourceId = sourceId, date = DateUtil.getDateOfToday())
            .map { articleEntities -> articleEntities.map(ArticleEntity::toDomain) }

    private fun updateNewestNews(sourceId: String): Observable<List<ArticleDomain>> =
        articleDao.updateNewestNews(sourceId)
            .map { articleEntities -> articleEntities.map(ArticleEntity::toDomain) }

    private fun updateAllTimeNews(sourceId: String): Observable<List<ArticleDomain>> =
        articleDao.updateAllTimeNews(sourceId)
            .map { articleEntities -> articleEntities.map(ArticleEntity::toDomain) }

    fun insertNews(articles: List<ArticleDomain>) =
        articleDao.insertNews(articles.map(ArticleDomain::toEntity))

    fun onArticlesFilterTypeChange(articlesFilterType: ArticlesFilterType, sourceId: String) =
        when (articlesFilterType) {
            ArticlesFilterType.TODAY -> updateTodayNews(sourceId = sourceId)
            ArticlesFilterType.NEWEST -> updateNewestNews(sourceId = sourceId)
            ArticlesFilterType.ALL_TIME -> updateAllTimeNews(sourceId = sourceId)
            ArticlesFilterType.DEFAULT -> updateAllTimeNews(sourceId = sourceId)
        }
}
