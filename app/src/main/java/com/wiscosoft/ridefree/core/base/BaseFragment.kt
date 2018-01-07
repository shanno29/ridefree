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
import dmax.dialog.SpotsDialog
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment<Binding : ViewDataBinding> : Fragment(), SupportFragmentInjector {

  internal val config = javaClass.getAnnotation(Config::class.java)
  internal val loadingUI: SpotsDialog by lazy { SpotsDialog(context, "") }
  internal val sub = CompositeDisposable()
  override val injector = KodeinInjector()
  lateinit var binding: Binding

  abstract fun onReady()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, bundle: Bundle?): View? {
    super.onCreateView(inflater, container, bundle)
    binding = inflate(layoutInflater, config.layout, null, false)!!
    activity?.title = config.title
    initializeInjector()
    return binding.root
  }

  override fun onStart() {
    super.onStart()
    onReady()
  }

  override fun onDestroyView() {
    sub.clear()
    destroyInjector()
    loadingUI.dismiss()
    super.onDestroyView()
  }
}

//setupFab()

// private fun setupFab() {
//    if (buttonImg != 0) {
//      binding.ROOT
//        .findViewById<FloatingActionButton>(R.id.fab)
//        .setImageResource(buttonImg)
//    }
//  }