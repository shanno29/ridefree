package com.wiscosoft.ridefree.core.main

import com.github.salomonbrys.kodein.Kodein.Module
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.jakewharton.rxbinding2.support.design.widget.RxNavigationView.itemSelections
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.wiscosoft.ridefree.R.layout.activity_main
import com.wiscosoft.ridefree.core.base.BaseActivity
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.extensions.goTo
import com.wiscosoft.ridefree.core.view.DrawerToggle
import com.wiscosoft.ridefree.databinding.ActivityMainBinding
import com.wiscosoft.ridefree.provider.router.Router
import io.reactivex.Flowable.just
import java.util.concurrent.TimeUnit.MILLISECONDS

@Config(title = "RideFree", layout = activity_main)
class MainActivity : BaseActivity<ActivityMainBinding>() {

  override fun provideOverridingModule() = Module { bind() from instance(this@MainActivity) }
  private val toggle: DrawerToggle by injector.instance()
  private val router: Router by injector.instance()
  private val vm: MainVM by injector.instance()

  override fun onReady() {
    setupNavigationDrawer()
    setupView()
  }

  private fun setupView() {
    just(vm.isLoggedIn())
      .doOnNext(toggle::setDrawerIndicator)
      .map { if (it) router.map() else router.splash() }
      .subscribe(this::goTo)
  }

  private fun setupNavigationDrawer() {
    title = config.title
    setSupportActionBar(binding.toolbar)
    toggle.setViews(binding.drawer, binding.toolbar)

//    observe(this, POST_CREATE)
//      .bindToLifecycle(provider)
//      .subscribe { toggle.syncState() }
//
//    observe(this, CONFIGURATION_CHANGED)
//      .bindToLifecycle(provider)
//      .subscribe(toggle::configChanged)

    itemSelections(binding.nav)
      .bindToLifecycle(provider)
      .doOnNext { binding.drawer.closeDrawers() }
      .delay(250, MILLISECONDS)
      .map { it.title }
      .filter { it != title }
      .map(router::fromTitle)
      .subscribe(this::goTo)
  }

}
