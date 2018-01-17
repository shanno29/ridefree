package com.wiscosoft.ridefree.core.base

import android.content.Intent
import android.os.IBinder
import com.wiscosoft.ridefree.core.app.debugLog
import io.reactivex.disposables.CompositeDisposable
import dagger.android.DaggerService

abstract class BaseService : DaggerService() {

  val sub = CompositeDisposable()

  override fun onCreate() {
    debugLog("onCreate")
    super.onCreate()
  }

  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int  {
    debugLog("onStartCommand")
    onReady()
    return START_STICKY
  }

  open fun onReady() {
    debugLog("onReady")
  }

  override fun onDestroy() {
    debugLog("onDestroy")
    sub.clear()
    super.onDestroy()
  }

  override fun onBind(intent: Intent?): IBinder? {
    debugLog("onBind")
    return null
  }

}



