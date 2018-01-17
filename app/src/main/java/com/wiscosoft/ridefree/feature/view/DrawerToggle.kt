package com.wiscosoft.ridefree.feature.view

import android.content.res.Configuration
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.wiscosoft.ridefree.R
import javax.inject.Inject

class DrawerToggle @Inject constructor(activity: AppCompatActivity) {
  private lateinit var toggle: ActionBarDrawerToggle

  val setViews: (DrawerLayout, Toolbar) -> Unit =  { drawer, toolbar ->
    toggle = ActionBarDrawerToggle(activity, drawer, toolbar, R.string.drawer_open, R.string.drawer_close)
  }

  val setDrawerIndicator: (Boolean) -> Unit = {
    toggle.isDrawerIndicatorEnabled = it
  }

  val configChanged: (Configuration) -> Unit = {
    toggle.onConfigurationChanged(it)
  }

  val syncState: () -> Unit = {
    toggle.syncState()
  }

}