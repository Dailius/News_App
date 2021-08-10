package com.dailiusprograming.newsapp.main.news.articles

import androidx.lifecycle.MutableLiveData
import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleDomain
import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleError
import com.dailiusprograming.newsapp.main.news.articles.repository.FetchArticlesUseCase
import com.dailiusprograming.newsapp.main.news.articles.utils.ArticlesFilterType
import com.dailiusprograming.newsapp.utils.fragment.BaseViewModel
import com.dailiusprograming.newsapp.utils.scheduler.Main
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject

class ArticlesViewModel @Inject constructor(
    private val articlesUseCase: FetchArticlesUseCase,
    @Main private val scheduler: Scheduler
) : BaseViewModel() {
    private var _articleList = MutableLiveData<List<ArticleDomain>>()
    val articleList = _articleList

    private var _isLoadingLiveData = MutableLiveData(false)
    val isLoadingLiveData = _isLoadingLiveData

    private var _errorMessage = MutableLiveData<ArticleError>()
    val errorMessage = _errorMessage

    private var articlesFilterType: ArticlesFilterType = ArticlesFilterType.TODAY
    private lateinit var sourceId: String

    fun onSourceIdLoaded(sourceId: String) {
        this.sourceId = sourceId
    }

    fun onRefreshSelected() {
        isLoadingLiveData.postValue(true)
        articlesUseCase.getArticles(
            sourceId = sourceId,
            articlesFilterType = articlesFilterType
        ).observeOn(scheduler)
            .subscribe(
                { result ->
                    _articleList.postValue(result)
                    _isLoadingLiveData.postValue(false)
                },
                { error ->
                    _errorMessage.postValue(
                        ArticleError(
                            type = ArticleError.Type.ACTION,
                            message = error.message
                        )
                    )
                    isLoadingLiveData.postValue(false)
                }
            ).addDisposable()
    }
}
