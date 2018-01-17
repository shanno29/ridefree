package com.wiscosoft.ridefree.core.base

import android.databinding.DataBindingUtil.inflate
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wiscosoft.ridefree.core.app.debugLog
import dagger.android.support.DaggerFragment
import dmax.dialog.SpotsDialog
import io.reactivex.disposables.CompositeDisposable
import kotlin.properties.Delegates

abstract class BaseFragment<Binding : ViewDataBinding> : DaggerFragment() {

  val layout: Layout = javaClass.getAnnotation(Layout::class.java)
  val title: Title = javaClass.getAnnotation(Title::class.java)
  val loadingUI by lazy { SpotsDialog(context) }
  var binding: Binding by Delegates.notNull()
  val sub = CompositeDisposable()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, bundle: Bundle?): View? {
    debugLog("onCreate")
    super.onCreateView(inflater, container, bundle)
    binding = inflate(inflater, layout.res, container, false)
    activity.title = title.text
    return binding.root
  }

  override fun onStart() {
    debugLog("onStart")
    super.onStart()
    onReady()
  }

  open fun onReady() {
    debugLog("onReady")
  }

  override fun onDestroy() {
    debugLog("onDestroy")
    sub.clear()
    binding.unbind()
    super.onDestroy()
  }

}

