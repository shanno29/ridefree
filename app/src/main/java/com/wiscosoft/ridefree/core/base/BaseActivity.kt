package com.wiscosoft.ridefree.core.base

import android.databinding.DataBindingUtil.setContentView
import android.databinding.ViewDataBinding
import android.os.Bundle
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.android.AppCompatActivityInjector
import com.trello.navi2.component.support.NaviAppCompatActivity
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.trello.rxlifecycle2.navi.NaviLifecycle.createActivityLifecycleProvider
import io.reactivex.Flowable.fromIterable

abstract class BaseActivity<Binding : ViewDataBinding> : NaviAppCompatActivity(), AppCompatActivityInjector {

  internal val config: Config = javaClass.getAnnotation(Config::class.java)
  override val injector: KodeinInjector = KodeinInjector()
  lateinit var provider: LifecycleProvider<ActivityEvent>
  lateinit var binding: Binding
  abstract fun onReady()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    provider = createActivityLifecycleProvider(this)
    binding = setContentView(this, config.layout)
    initializeInjector()
    setupToolbar()
  }

  override fun onStart() {
    super.onStart()
    onReady()
  }

  override fun onDestroy() {
    binding.unbind()
    destroyInjector()
    super.onDestroy()
  }

  private fun setupToolbar() {
    supportFragmentManager.addOnBackStackChangedListener {
      fromIterable(supportFragmentManager.fragments).lastElement()
        .bindToLifecycle(provider)
        .cast(BaseFragment::class.java)
        .map { it.config.title }
        .subscribe(this::setTitle)

    }
  }

}


