package com.wiscosoft.ridefree.core.base

import android.databinding.DataBindingUtil.*
import android.databinding.ViewDataBinding
import android.os.Bundle
import com.wiscosoft.ridefree.core.app.debugLog
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import kotlin.properties.Delegates

abstract class BaseActivity<Binding : ViewDataBinding> : DaggerAppCompatActivity() {

  val layout: Layout = javaClass.getAnnotation(Layout::class.java)
  var binding: Binding by Delegates.notNull()
  val sub = CompositeDisposable()

  override fun onCreate(bundle: Bundle?) {
    debugLog("onCreate")
    super.onCreate(bundle)
    binding = setContentView(this, layout.res)
  }

  override fun onStart() {
    debugLog("onStart")
    super.onStart()
    onReady()
  }

  open fun onReady() {
    debugLog("onReady")
  }

  public override fun onDestroy() {
    debugLog("onDestroy")
    sub.clear()
    binding.unbind()
    super.onDestroy()
  }

  fun titleUpdates() {
    debugLog("titleUpdates")
    sub.add(backStackUpdates()
      .filter { it is BaseFragment<*> }
      .cast(BaseFragment::class.java)
      .map { it.title.text }
      .subscribe(this::setTitle)
    )
  }

}

