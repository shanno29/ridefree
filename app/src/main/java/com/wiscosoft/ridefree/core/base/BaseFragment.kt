package com.wiscosoft.ridefree.core.base

import android.databinding.DataBindingUtil.inflate
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.android.SupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment<Binding : ViewDataBinding> : Fragment(), SupportFragmentInjector {

  internal var config = javaClass.getAnnotation(Config::class.java)
  override val injector: KodeinInjector = KodeinInjector()
  internal val sub = CompositeDisposable()
  lateinit var binding: Binding

  abstract fun onReady()

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, bundle: Bundle?): View? {
    super.onCreateView(inflater, container, bundle)
    binding = inflate(inflater, config.layout, container, false)
    activity.title = config.title
    return binding.root
  }

  override fun onStart() {
    super.onStart()
    initializeInjector()
    onReady()
  }

  override fun onDestroyView() {
    sub.clear()
    binding.unbind()
    super.onDestroyView()
  }

}
