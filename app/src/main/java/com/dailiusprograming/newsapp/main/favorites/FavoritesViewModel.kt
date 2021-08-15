package com.dailiusprograming.newsapp.main.favorites

import androidx.lifecycle.MutableLiveData
import com.dailiusprograming.newsapp.main.favorites.repository.FetchFavoritesUseCase
import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleDomain
import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleError
import com.dailiusprograming.newsapp.utils.fragment.BaseViewModel
import com.dailiusprograming.newsapp.utils.fragment.LiveEvent
import com.dailiusprograming.newsapp.utils.scheduler.Main
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesUseCase: FetchFavoritesUseCase,
    @Main private val scheduler: Scheduler
) : BaseViewModel() {

    private var _favoritesList = MutableLiveData<List<ArticleDomain>>()
    val favoritesList = _favoritesList

    private var _errorMessage = LiveEvent<ArticleError>()
    val errorMessage = _errorMessage

}
