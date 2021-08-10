package com.dailiusprograming.newsapp.main.news.articles

import com.dailiusprograming.newsapp.main.news.articles.repository.FetchArticlesUseCase
import com.dailiusprograming.newsapp.utils.fragment.BaseViewModel
import com.dailiusprograming.newsapp.utils.scheduler.Main
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject

class ArticlesViewModel @Inject constructor(
    private val articlesUseCase: FetchArticlesUseCase,
    @Main private val scheduler: Scheduler
) : BaseViewModel() {

}
