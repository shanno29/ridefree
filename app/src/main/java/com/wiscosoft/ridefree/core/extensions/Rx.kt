package com.wiscosoft.ridefree.core.extensions

import android.util.Log
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.Math.abs
import java.util.UUID.randomUUID


fun randomId(): Int = abs(randomUUID().hashCode())

fun <T> threads(): FlowableTransformer<T, T> = FlowableTransformer {
  it.subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
}

fun <T> log(): FlowableTransformer<T, T> = FlowableTransformer {
  it.doOnNext { Log.d("Flowable", it.toString()) }
    .doOnError { Log.e("Flowable", it.localizedMessage) }
}

infix fun <T> Boolean.then(param: () -> T): T? = if (this) param() else null
