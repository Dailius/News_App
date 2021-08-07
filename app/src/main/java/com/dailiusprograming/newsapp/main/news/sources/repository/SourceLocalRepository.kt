package com.dailiusprograming.newsapp.main.news.sources.repository

import com.dailiusprograming.newsapp.main.news.sources.data.local.SourceDao
import com.dailiusprograming.newsapp.main.news.sources.data.model.SourceDomain
import com.dailiusprograming.newsapp.main.news.sources.data.model.SourceEntity
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class SourceLocalRepository @Inject constructor(
    private val sourceDao: SourceDao
) {
    fun getSources(): Observable<List<SourceDomain>> =
        sourceDao.getSources().map { sourceEntities -> sourceEntities.map(SourceEntity::toDomain) }

    fun insertSources(sources: List<SourceDomain>) =
        sourceDao.insertSources(sources.map(SourceDomain::toEntity))

    fun clearSources() =
        sourceDao.clearSources()
}
