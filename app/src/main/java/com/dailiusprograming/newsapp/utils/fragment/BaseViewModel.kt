package com.dailiusprograming.newsapp.utils.fragment

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    private val disposables = CompositeDisposable()

    fun Disposable.addDisposable(){
        disposables.add(this)
    }

    override fun onCleared() {
        if (!disposables.isDisposed){
            disposables.clear()
        }
        super.onCleared()
    }
}
