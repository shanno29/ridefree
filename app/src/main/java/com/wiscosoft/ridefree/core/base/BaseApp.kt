package com.wiscosoft.ridefree.core.base

import com.wiscosoft.ridefree.core.app.debugLog
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

abstract class BaseApp : DaggerApplication() {

  abstract val graph: AndroidInjector<out BaseApp>

  override fun onCreate() {
    debugLog("onCreate")
    super.onCreate()
    onReady()
  }

  open fun onReady() {
    debugLog("onReady")
  }

  override fun onTerminate() {
    debugLog("onTerminate")
    super.onTerminate()
  }

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> = graph

}


