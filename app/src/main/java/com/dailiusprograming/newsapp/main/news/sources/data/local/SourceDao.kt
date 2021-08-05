package com.dailiusprograming.newsapp.main.news.sources.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dailiusprograming.newsapp.main.news.sources.data.model.SourceEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface SourceDao {
    @Query("SELECT * FROM source")
    fun getSources(): Observable<List<SourceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSources(sources: List<SourceEntity>): Completable

    @Query("DELETE FROM source")
    fun clearSources(): Completable
}
