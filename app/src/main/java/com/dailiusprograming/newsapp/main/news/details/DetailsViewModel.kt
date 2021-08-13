package com.dailiusprograming.newsapp.main.news.details

import androidx.lifecycle.MutableLiveData
import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleDomain
import com.dailiusprograming.newsapp.utils.fragment.BaseViewModel

class DetailsViewModel: BaseViewModel() {
    private var _articleDetails = MutableLiveData<ArticleDomain>()
    val articleDetails = _articleDetails

    fun onArticleDetailsLoaded(articleDomain: ArticleDomain?) {
        if (articleDomain != null){
            _articleDetails.postValue(articleDomain!!)
        }
    }
}