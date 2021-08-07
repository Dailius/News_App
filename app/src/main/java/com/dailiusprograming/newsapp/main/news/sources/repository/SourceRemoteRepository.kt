package com.dailiusprograming.newsapp.main.news.sources.repository

import com.dailiusprograming.newsapp.main.news.sources.data.model.SourceDomain
import com.dailiusprograming.newsapp.main.news.sources.data.model.SourceItemResponse
import com.dailiusprograming.newsapp.utils.network.ApiService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SourceRemoteRepository @Inject constructor(
    private val service: ApiService
) {
    fun getSources(): Single<List<SourceDomain>> =
        service.getSource()
            .map { sourceResponse -> sourceResponse.sources.map(SourceItemResponse::toDomain) }
}
