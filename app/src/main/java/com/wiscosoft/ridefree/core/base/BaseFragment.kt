package com.wiscosoft.ridefree.core.base

import android.databinding.DataBindingUtil.inflate
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.android.SupportFragmentInjector
import com.trello.navi2.component.support.NaviFragment
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.navi.NaviLifecycle.createFragmentLifecycleProvider

abstract class BaseFragment<Binding : ViewDataBinding> : NaviFragment(), SupportFragmentInjector {

  internal val config: Config = javaClass.getAnnotation(Config::class.java)
  override val injector: KodeinInjector = KodeinInjector()
  lateinit var provider: LifecycleProvider<FragmentEvent>
  lateinit var binding: Binding
  abstract fun onReady()

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, bundle: Bundle?): View? {
    super.onCreateView(inflater, container, bundle)
    binding = inflate(inflater, config.layout, container, false)
    provider = createFragmentLifecycleProvider(this)
    activity.title = config.title
    setHasOptionsMenu(true)
    initializeInjector()
    return binding.root
  }

  override fun onStart() {
    super.onStart()
    onReady()
  }

  override fun onDestroyView() {
    binding.unbind()
    destroyInjector()
    super.onDestroyView()
  }

}
