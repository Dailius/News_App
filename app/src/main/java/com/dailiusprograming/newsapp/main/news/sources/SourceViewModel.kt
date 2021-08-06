package com.dailiusprograming.newsapp.main.news.sources

import androidx.lifecycle.MutableLiveData
import com.dailiusprograming.newsapp.main.news.sources.data.model.SourceDomain
import com.dailiusprograming.newsapp.main.news.sources.data.model.SourceError
import com.dailiusprograming.newsapp.main.news.sources.repository.FetchSourcesUseCase
import com.dailiusprograming.newsapp.utils.fragment.BaseViewModel
import com.dailiusprograming.newsapp.utils.fragment.LiveEvent
import com.dailiusprograming.newsapp.utils.scheduler.Main
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject

@HiltViewModel
class SourceViewModel @Inject constructor(
    private val sourcesUseCase: FetchSourcesUseCase,
    @Main private val scheduler: Scheduler
) : BaseViewModel() {
    private val _sourceList = MutableLiveData<List<SourceDomain>>()
    val sourceList = _sourceList

    private val _isLoadingLiveData = MutableLiveData(false)
    val isLoadingLiveData = _isLoadingLiveData

    private val _errorMessage = LiveEvent<SourceError>()
    val errorMessage = _errorMessage

    fun onRefreshSelected() {
        sourcesUseCase.getSources()
        isLoadingLiveData.postValue(true)
        getSourceResponse()
    }

    private fun getSourceResponse() {
        sourcesUseCase.getSources()
            .observeOn(scheduler)
            .subscribe(
                { result ->
                    sourceList.postValue(result)
                    isLoadingLiveData.postValue(false)
                },
                { error ->
                    errorMessage.postValue(SourceError(error.message))
                }
            ).addDisposable()
    }
}
