package com.dailiusprograming.newsapp.utils.scheduler

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object SchedulersModule {
    @Provides
    @IO
    fun providesIoScheduler(): Scheduler = Schedulers.io()

    @Provides
    @Main
    fun providesMainScheduler(): Scheduler = AndroidSchedulers.mainThread()
}

@Qualifier
annotation class IO

@Qualifier
annotation class Main
