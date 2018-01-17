package com.wiscosoft.ridefree.core.app

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyRecyclerView
import java.lang.Math.abs
import java.util.UUID.randomUUID

fun randomId(): Long {
  return abs(randomUUID().hashCode()).toLong()
}

fun EpoxyRecyclerView.withModels(buildModelsCallback: EpoxyController.() -> Unit) {
  setControllerAndBuildModels(object : EpoxyController() {
    override fun buildModels() {
      buildModelsCallback()
    }
  })
}

fun Any.debugLog(any: Any) { Log.d(this::javaClass.name, any.toString()) }

fun Any.errorLog(throwable: Throwable) { Log.e(this::javaClass.name, throwable.localizedMessage) }


fun Fragment.inputManager(): InputMethodManager { return context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }

fun Fragment.hideKeyboard() { view?.let { inputManager().hideSoftInputFromWindow(it.windowToken, InputMethodManager.SHOW_FORCED) } }

fun Fragment.showMessage(msg: String) { view?.let { Snackbar.make(it, msg, Snackbar.LENGTH_LONG).show() } }

fun Fragment.showError(err: Throwable) { showMessage(err.localizedMessage) }

