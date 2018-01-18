package com.wiscosoft.ridefree.feature.view

import android.content.res.Configuration
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.wiscosoft.ridefree.R
import javax.inject.Inject

class DrawerToggle @Inject constructor(activity: AppCompatActivity) {

  private var isReady = false

  private lateinit var toggle: ActionBarDrawerToggle

  val setViews: (DrawerLayout, Toolbar) -> Unit =  { drawer, toolbar ->
    toggle = ActionBarDrawerToggle(activity, drawer, toolbar, R.string.drawer_open, R.string.drawer_close)
    isReady = true
  }

  val setDrawerIndicator: (Boolean) -> Unit = {
    if (isReady) toggle.isDrawerIndicatorEnabled = it
  }

  val configChanged: (Configuration) -> Unit = {
    if (isReady) toggle::onConfigurationChanged
  }

  val syncState: () -> Unit = {
    if (isReady) toggle.syncState()
  }

}
