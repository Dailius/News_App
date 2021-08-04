package com.dailiusprograming.newsapp.tutorial

import androidx.lifecycle.MutableLiveData
import com.dailiusprograming.newsapp.tutorial.model.TutorialPage
import com.dailiusprograming.newsapp.tutorial.repository.TutorialRepository
import com.dailiusprograming.newsapp.utils.storage.StorageGeneral
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TutorialViewModel @Inject constructor(
    private val storageGeneral: StorageGeneral,
    private val repository: TutorialRepository
) {
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
}
