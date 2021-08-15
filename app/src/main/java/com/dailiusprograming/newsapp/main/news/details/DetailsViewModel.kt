package com.dailiusprograming.newsapp.main.news.details

import androidx.lifecycle.MutableLiveData
import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleDomain
import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleEntity
import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleError
import com.dailiusprograming.newsapp.main.news.details.repository.FetchDetailsUseCase
import com.dailiusprograming.newsapp.utils.fragment.BaseViewModel
import com.dailiusprograming.newsapp.utils.fragment.LiveEvent
import com.dailiusprograming.newsapp.utils.scheduler.Main
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsUseCase: FetchDetailsUseCase,
    @Main private val scheduler: Scheduler
) : BaseViewModel() {
    private var _articleDetails = MutableLiveData<ArticleDomain>()
    val articleDetails = _articleDetails

    private val _errorMessage = LiveEvent<ArticleError>()
    val errorMessage = _errorMessage

    fun onArticleDetailsLoaded(articleDomain: ArticleDomain?) {
        if (articleDomain != null) {
            _articleDetails.postValue(articleDomain!!)
        }
    }

    fun updateFavoritesItem(checkedFavorites: Boolean) {
        _articleDetails.value?.let { articleId ->
            detailsUseCase.updateFavoritesItem(articleId.url, checkedFavorites)
                .observeOn(scheduler)
                .subscribe(
                    { },
                    { error ->
                        errorMessage.postValue(
                            ArticleError(message = error.message)
                        )
                    }
                ).addDisposable()
        }
    }
}
