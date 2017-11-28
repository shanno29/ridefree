package com.wiscosoft.ridefree.feature.activity.main

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import com.github.salomonbrys.kodein.instance
import com.jakewharton.rxbinding2.support.design.widget.RxNavigationView.itemSelections
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.wiscosoft.ridefree.R.layout.activity_main
import com.wiscosoft.ridefree.R.string.drawer_close
import com.wiscosoft.ridefree.R.string.drawer_open
import com.wiscosoft.ridefree.core.base.BaseActivity
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.extensions.goTo
import com.wiscosoft.ridefree.databinding.ActivityMainBinding
import com.wiscosoft.ridefree.provider.router.Router
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit.MILLISECONDS

@Config(title = "RideFree", layout = activity_main)
class MainActivity : BaseActivity<ActivityMainBinding>() {

  private lateinit var toggle: ActionBarDrawerToggle
  private val router: Router by injector.instance()
  private val vm: MainVM by injector.instance()

  private fun drawerToggle(): ActionBarDrawerToggle {
    val t = ActionBarDrawerToggle(this, binding.drawer, binding.toolbar, drawer_open, drawer_close)
    t.isDrawerIndicatorEnabled = true
    return t
  }

  override fun onReady() {
    setSupportActionBar(binding.toolbar)
    //supportActionBar?.setDisplayHomeAsUpEnabled(true)
    //supportActionBar?.setDisplayShowHomeEnabled(true)
    toggle = drawerToggle()
    binding.drawer.addDrawerListener(toggle)
    setupNavigationDrawer()
    setupFragment()
  }

  override fun onPostCreate(bundle: Bundle?) {
    super.onPostCreate(bundle)
    toggle.syncState()
  }

  override fun onConfigurationChanged(config: Configuration) {
    super.onConfigurationChanged(config)
    toggle.onConfigurationChanged(config)
  }

  private fun setupFragment() {
    Flowable.just(vm.isLoggedIn())
      .bindToLifecycle(provider)
      .map { if (it) router.map() else router.login() }
      .subscribe(this::goTo)

  }

  private fun setupNavigationDrawer() {
    itemSelections(binding.nav)
      .bindToLifecycle(provider)
      .doOnNext { binding.drawer.closeDrawers() }
      .map { it.title.toString() }
      .delay(250, MILLISECONDS)
      .filter { it != title }
      .map(router::fromTitle)
      .subscribe(this::goTo)
  }

}