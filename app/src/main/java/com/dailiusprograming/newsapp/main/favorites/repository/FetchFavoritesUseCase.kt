package com.dailiusprograming.newsapp.main.favorites.repository

import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleDomain
import com.dailiusprograming.newsapp.utils.scheduler.IO
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject

class FetchFavoritesUseCase @Inject constructor(
    private val localRepository: FavoritesLocalRepository,
    @IO private val scheduler: Scheduler
) {
    fun getFavorites(): Observable<List<ArticleDomain>> =
        localRepository.getFavorites()
            .subscribeOn(scheduler)
}
