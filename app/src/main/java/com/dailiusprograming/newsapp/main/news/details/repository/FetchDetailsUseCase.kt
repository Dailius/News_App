package com.dailiusprograming.newsapp.main.news.details.repository

import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleDomain
import com.dailiusprograming.newsapp.utils.scheduler.IO
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FetchDetailsUseCase @Inject constructor(
    private val localRepository: DetailsLocalRepository,
    @IO private val scheduler: Scheduler
) {
    fun getDetails(articleId: String): Single<ArticleDomain> =
        localRepository.getArticleDetails(articleId)
            .subscribeOn(scheduler)

    fun updateFavoritesItem(url: String, favorites: Boolean): Completable =
        localRepository.updateFavoritesItem(url = url, favorites = favorites)
            .subscribeOn(scheduler)
}
