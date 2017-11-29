package com.wiscosoft.ridefree.core.view

import android.content.res.Configuration
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

class DrawerToggle(private val activity: AppCompatActivity, private val openTextRes: Int, private val closeTextRes: Int) {
  private lateinit var toggle: ActionBarDrawerToggle

  fun setViews(drawer: DrawerLayout, toolbar: Toolbar) {
    toggle = ActionBarDrawerToggle(activity, drawer, toolbar, openTextRes, closeTextRes)
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