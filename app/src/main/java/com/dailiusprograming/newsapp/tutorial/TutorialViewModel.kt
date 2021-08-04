package com.dailiusprograming.newsapp.tutorial

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dailiusprograming.newsapp.tutorial.model.TutorialPage
import com.dailiusprograming.newsapp.tutorial.repository.TutorialRepository
import com.dailiusprograming.newsapp.utils.storage.StorageGeneral
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TutorialViewModel @Inject constructor(
    private val storageGeneral: StorageGeneral,
    private val repository: TutorialRepository
) : ViewModel() {
    private val _pageList = MutableLiveData<List<TutorialPage>>()
    val pageList = _pageList

    init {
        getPageList()
    }

    fun onTutorialFinished(isTutorialDisplayed: Boolean) {
        storageGeneral.isTutorialDisplayed = isTutorialDisplayed
    }

    private fun getPageList() {
        _pageList.postValue(repository.providePageList())
    }

    fun openNextPage(activePage: Int) = when {
        activePage + 1 < pageList.value?.size ?: 0 -> true
        else -> false
    }
}
