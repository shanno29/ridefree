package com.wiscosoft.ridefree.feature.main

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.Kodein.Module
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.wiscosoft.ridefree.R
import com.wiscosoft.ridefree.core.base.BaseActivity
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.goTo
import com.wiscosoft.ridefree.databinding.ActivityMainBinding
import com.wiscosoft.ridefree.provider.api.entity.user.UserApi
import com.wiscosoft.ridefree.provider.redux.State
import com.wiscosoft.ridefree.provider.router.Router
import redux.api.Store

@Config("", R.layout.activity_main)
class MainActivity : BaseActivity<ActivityMainBinding>() {

  override fun provideOverridingModule() = Module { bind() from instance(this@MainActivity) }
  //private val toggle: DrawerToggle by injector.instance()
  private val router: Router by injector.instance()
  private val vm: MainVM by injector.instance()

  override fun onReady() {
    // toggle::setDrawerIndicator
    goTo(router.register)
  }

  private fun setupNavigationDrawer() {
//    setSupportActionBar(binding?.toolbar)
//    binding.toolbar.supportActionBar?.setDisplayHomeAsUpEnabled(false)
//    toggle.setViews(binding?.drawer, binding?.toolbar)
//
//    sub.add(itemSelections(binding?.nav)
//      .doOnEach { binding?.drawer?.closeDrawers() }
//      .delay(250, MILLISECONDS)
//      .map { it.title }
//      .filter { it != title }
//      .subscribe { goTo(router.fromTitle(it)) }
//    )
  }

  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)
    //toggle.syncState()
  }

  override fun onConfigurationChanged(newConfig: Configuration) {
    super.onConfigurationChanged(newConfig)
    //toggle.configChanged(newConfig)
  }

}

class DrawerToggle(private val activity: AppCompatActivity) {
  private lateinit var toggle: ActionBarDrawerToggle

  fun setViews(drawer: DrawerLayout, toolbar: Toolbar) {
    toggle = ActionBarDrawerToggle(activity, drawer, toolbar, R.string.drawer_open, R.string.drawer_close)
  }

  fun setDrawerIndicator(flag: Boolean) {
    toggle.isDrawerIndicatorEnabled = flag
  }

  fun syncState() {
    toggle.syncState()
  }

  fun configChanged(newConfig: Configuration) {
    toggle.onConfigurationChanged(newConfig)
  }
}

class MainVM(private val userApi: UserApi, private val store: Store<State>)

val mainModule = Kodein.Module {

  //  bind<DrawerToggle>() with singleton {
//    val activity: MainActivity = instance()
//    DrawerToggle(activity)
//  }

  bind<MainVM>() with singleton {
    val userApi: UserApi = instance()
    val store: Store<State> = instance()
    MainVM(userApi, store)
  }
}
