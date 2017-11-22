package com.wiscosoft.ridefree.core.extensions

import android.util.Log
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*



fun randomId(): Int = Math.abs(UUID.randomUUID().hashCode())

fun <T> threads(): FlowableTransformer<T, T> = FlowableTransformer {
  it.subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
}

fun <T> log(): FlowableTransformer<T, T> = FlowableTransformer {
    it.doOnNext { Log.d("Flowable", it.toString()) }
        .doOnError { Log.e("Flowable", it.localizedMessage) }
}
