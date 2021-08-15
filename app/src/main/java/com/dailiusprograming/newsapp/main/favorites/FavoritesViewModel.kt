package com.dailiusprograming.newsapp.main.favorites

import com.dailiusprograming.newsapp.main.favorites.repository.FetchFavoritesUseCase
import com.dailiusprograming.newsapp.utils.fragment.BaseViewModel
import com.dailiusprograming.newsapp.utils.scheduler.Main
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesUseCase: FetchFavoritesUseCase,
    @Main private val scheduler: Scheduler
) : BaseViewModel(){

}
