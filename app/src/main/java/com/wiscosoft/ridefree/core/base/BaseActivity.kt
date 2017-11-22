package com.wiscosoft.ridefree.core.base

import android.databinding.DataBindingUtil.setContentView
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.android.AppCompatActivityInjector
import io.reactivex.Flowable.fromIterable
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<Binding : ViewDataBinding> : AppCompatActivity(), AppCompatActivityInjector {

  internal var config = javaClass.getAnnotation(Config::class.java)
  override val injector: KodeinInjector = KodeinInjector()
  internal val sub = CompositeDisposable()

  lateinit var binding: Binding
  abstract fun onReady()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = setContentView(this, config.layout)
    initializeInjector()
    setupToolbar()
  }

  override fun onStart() {
    super.onStart()
    onReady()
  }

  override fun onDestroy() {
    sub.clear()
    binding.unbind()
    super.onDestroy()
  }

  private fun setupToolbar() {
    supportFragmentManager.addOnBackStackChangedListener {
      sub.add(fromIterable(supportFragmentManager.fragments)
          .lastElement()
          .cast(BaseFragment::class.java)
          .map { it.config.title }
          .subscribe(this::setTitle)
      )
    }
  }

}


