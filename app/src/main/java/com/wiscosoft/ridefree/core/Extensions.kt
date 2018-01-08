package com.wiscosoft.ridefree.core

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.wiscosoft.ridefree.R
import com.wiscosoft.ridefree.R.anim.slide_from_left
import com.wiscosoft.ridefree.R.anim.slide_to_left
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun Any.log(msg: String) = Log.d(this::class.qualifiedName!!, msg)

/*
 * RxJAVA
 */

fun <T> Flowable<T>.setThreads(): Flowable<T> {
  return this
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Single<T>.setThreads(): Single<T> {
  return this
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
}

val <T> Single<T>.flowable: Flowable<T> get() = this.toFlowable()

val <T> Maybe<T>.flowable: Flowable<T> get() = this.toFlowable()

val <T> Observable<T>.flowable: Flowable<T> get() = this.toFlowable(BackpressureStrategy.ERROR)

/*
 * Activity
 */

fun AppCompatActivity.goTo(fragment: Fragment) {
  supportFragmentManager.beginTransaction()
    .setCustomAnimations(slide_from_left, slide_to_left, slide_from_left, slide_to_left)
    .replace(R.id.container, fragment)
    .addToBackStack(null)
    .commit()
}

val TextInputLayout.value: String get() = this.editText?.text.toString().trim()

fun Fragment.goTo(fragment: Fragment) = (activity as? AppCompatActivity)?.goTo(fragment)

/*
 * Fragment
 */

fun Fragment.hideKeyboard() {
  (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
    .hideSoftInputFromWindow(view?.windowToken, InputMethodManager.SHOW_FORCED)
}

fun Fragment.showMessage(msg: String) {
  view?.let { Snackbar.make(it, msg, Snackbar.LENGTH_LONG).show() }
}

fun Fragment.showError(throwable: Throwable) {
  view?.let { this.showMessage(throwable.localizedMessage) }
}

fun Fragment.goBack() = view?.let { hideKeyboard(); fragmentManager?.popBackStack() }


