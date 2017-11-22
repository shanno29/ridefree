package com.wiscosoft.ridefree.core.extensions

import android.app.Activity.INPUT_METHOD_SERVICE
import android.content.Context
import android.support.design.widget.Snackbar.LENGTH_LONG
import android.support.design.widget.Snackbar.make
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.wiscosoft.ridefree.R
import com.wiscosoft.ridefree.R.anim.slide_from_left
import com.wiscosoft.ridefree.R.anim.slide_to_left


/*
 * Fragment
 */

fun Fragment.showError(throwable: Throwable) = showMessage(throwable.localizedMessage)

fun Fragment.showMessage(msg: String) = view?.let { make(it, msg, LENGTH_LONG).show() }

fun Fragment.hideToolbar() = (activity as AppCompatActivity).supportActionBar?.hide()

fun Fragment.hideKeyboard() = view?.let { activity.hideKeyboard(it) }

fun Fragment.goTo(fragment: Fragment) = (activity as AppCompatActivity).goTo(fragment)

fun Fragment.goBack() = apply { hideKeyboard() }.apply { fragmentManager.popBackStack() }


/*
 * Context
 */

fun Context.hideKeyboard(view: View) {
  (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
      .hideSoftInputFromWindow(view.windowToken, 0)
}


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


/*
 * RecyclerView
 */

fun EpoxyRecyclerView.withModels(buildModelsCallback: EpoxyController.() -> Unit) {
  setControllerAndBuildModels(object : EpoxyController() {
    override fun buildModels() {
      buildModelsCallback()
    }
  })
}


