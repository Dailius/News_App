package com.dailiusprograming.newsapp.main.news.sources.repository

import com.dailiusprograming.newsapp.main.news.sources.data.model.SourceDomain
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class FetchSourcesUseCase @Inject constructor(
    private val remoteRepository: SourceRemoteRepository,
    private val localRepository: SourceLocalRepository
) {
    fun getSources(): Observable<List<SourceDomain>> =
        Observable.merge(
            localRepository.getSources(),
            remoteRepository.getSources()
                .flatMapCompletable { list ->
                    localRepository.clearSources()
                        .andThen(localRepository.insertSources(list))
                }.toObservable()
        )
}