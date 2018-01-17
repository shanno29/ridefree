package com.wiscosoft.ridefree.core.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.wiscosoft.ridefree.R
import com.wiscosoft.ridefree.R.anim.*
import com.wiscosoft.ridefree.provider.rx.setThreads
import io.reactivex.Observable

fun FragmentActivity.backStackSize(): Int = supportFragmentManager.backStackEntryCount

fun FragmentActivity.goBack() { supportFragmentManager.popBackStack() }

fun FragmentActivity.goTo(fragment: Fragment) {
  this.supportFragmentManager.beginTransaction()
    .setCustomAnimations(slide_from_left, slide_to_left, slide_from_left, slide_to_left)
    .replace(R.id.container, fragment)
    .addToBackStack(null)
    .commit()
}

fun FragmentActivity.backStackUpdates(): Observable<List<Fragment>> {
  return Observable.create<List<Fragment>> {
    supportFragmentManager.addOnBackStackChangedListener {
      if (it.isDisposed) it.onComplete()
      else it.onNext(supportFragmentManager.fragments)
    }
  }.setThreads()
}

fun Fragment.goBack() = activity.goBack()
fun Fragment.goTo(fragment: Fragment) = activity.goTo(fragment)
