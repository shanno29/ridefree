package com.wiscosoft.ridefree.provider.rx

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProvider(private val backgroundScheduler: Scheduler, private val foregroundScheduler: Scheduler) {

  fun <T> getSchedulersForObservable(): (Observable<T>) -> Observable<T> {
    return { observable: Observable<T> -> observable
      .subscribeOn(backgroundScheduler)
      .observeOn(foregroundScheduler)
    }
  }

  fun <T> getSchedulersForSingle(): (Single<T>) -> Single<T> {
    return { single: Single<T> -> single
      .subscribeOn(backgroundScheduler)
      .observeOn(foregroundScheduler)
    }
  }

  fun getSchedulersForCompletable(): (Completable) -> Completable {
    return { completable: Completable -> completable
      .subscribeOn(backgroundScheduler)
      .observeOn(foregroundScheduler)
    }
  }

  fun <T> getSchedulersForFlowable(): (Flowable<T>) -> Flowable<T> {
    return { flowable: Flowable<T> -> flowable
      .subscribeOn(backgroundScheduler)
      .observeOn(foregroundScheduler)
    }
  }
}


fun <T> Observable<T>.setThreads(): Observable<T> {
  return this
    .observeOn(AndroidSchedulers.mainThread())
    .subscribeOn(Schedulers.io())
}

fun <T> Single<T>.setThreads(): Single<T> {
  return this
    .observeOn(AndroidSchedulers.mainThread())
    .subscribeOn(Schedulers.io())
}

inline fun <X, Y> Observable<X>.flatMapObs(crossinline body: () -> Flowable<Y>): Observable<Y> =
  this.flatMap { body().toObservable() }

inline fun <X, Y> Observable<X>.flatMapObs(crossinline body: (X) -> Flowable<Y>): Observable<Y> =
  this.flatMap { body(it).toObservable() }

