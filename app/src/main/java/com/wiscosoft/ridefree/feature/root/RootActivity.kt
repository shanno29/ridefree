package com.wiscosoft.ridefree.feature.root

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity.START
import com.jakewharton.rxbinding2.support.design.widget.RxNavigationView.itemSelections
import com.wiscosoft.ridefree.R
import com.wiscosoft.ridefree.databinding.ActivityRootBinding
import com.wiscosoft.ridefree.feature.ui.about.AboutFragment
import com.wiscosoft.ridefree.feature.ui.account.logout.LogoutFragment
import com.wiscosoft.ridefree.feature.ui.map.MapFragment
import com.wiscosoft.ridefree.feature.ui.payments.PaymentListFragment
import com.wiscosoft.ridefree.feature.ui.promos.PromosFragment
import com.wiscosoft.ridefree.feature.ui.settings.SettingsFragment
import com.wiscosoft.ridefree.feature.view.DrawerToggle
import com.wiscosoft.ridefree.core.base.BaseActivity
import com.wiscosoft.ridefree.core.base.Layout
import com.wiscosoft.ridefree.core.base.goTo
import com.wiscosoft.ridefree.feature.ui.rides.RideListFragment
import javax.inject.Inject

@Layout(R.layout.activity_root)
class RootActivity : BaseActivity<ActivityRootBinding>() {

  @Inject lateinit var toggle: DrawerToggle

  override fun onReady() {
    super.onReady()
    //setupToolbar()
    //setupNavDrawer()
    //goTo(RegisterFragment())
  }

  fun setupToolbar() {
    setSupportActionBar(binding.toolbar)
    toggle.setViews(binding.drawer, binding.toolbar)
    toggle.setDrawerIndicator(true)
    titleUpdates()
  }

  fun setupNavDrawer() {
    sub.add(itemSelections(binding.nav)
      .doOnEach { binding.drawer.closeDrawers() }
      .filter { this.title != it.title }
      .map { it.title }
      .subscribe {
        goTo(when (it) {
          "Home" -> MapFragment()
          "Rides" -> RideListFragment()
          "Promos" -> PromosFragment()
          "Payment" -> PaymentListFragment()
          "Settings" -> SettingsFragment()
          "Logout" -> LogoutFragment()
          "About" -> AboutFragment()
          else -> Fragment()
        })
      }
    )
  }

  override fun onBackPressed() = when {
    binding.drawer.isDrawerOpen(START) -> binding.drawer.closeDrawers()
    else -> super.onBackPressed()
  }

  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)
    toggle.syncState()
  }

  override fun onConfigurationChanged(newConfig: Configuration) {
    super.onConfigurationChanged(newConfig)
    toggle.configChanged(newConfig)
  }

}
