package com.dailiusprograming.newsapp.main.news.articles.repository

import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleDomain
import com.dailiusprograming.newsapp.main.news.articles.utils.ArticlesFilterType
import com.dailiusprograming.newsapp.utils.scheduler.IO
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject

class FetchArticlesUseCase @Inject constructor(
    private val remoteRepository: ArticleRemoteRepository,
    private val localRepository: ArticleLocalRepository,
    @IO private val scheduler: Scheduler
) {
    fun getArticles(
        sourceId: String,
        articlesFilterType: ArticlesFilterType
    ): Observable<List<ArticleDomain>> =
        Observable.merge(
            localRepository.onArticlesFilterTypeChange(
                articlesFilterType = articlesFilterType,
                sourceId = sourceId
            ),
            remoteRepository.getArticles(sourceId = sourceId)
                .flatMapCompletable { list ->
                    localRepository.insertNews(list)
                }.toObservable()
        ).subscribeOn(scheduler)

    fun updateArticles(
        sourceId: String,
        articlesFilterType: ArticlesFilterType
    ): Observable<List<ArticleDomain>> = localRepository.onArticlesFilterTypeChange(
        articlesFilterType = articlesFilterType,
        sourceId = sourceId
    ).subscribeOn(scheduler)
}
