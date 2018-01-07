package com.wiscosoft.ridefree.core.base

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.android.ServiceInjector
import io.reactivex.disposables.CompositeDisposable

abstract class BaseService : Service(), ServiceInjector {

  internal val sub: CompositeDisposable = CompositeDisposable()
  override val injector: KodeinInjector = KodeinInjector()
  abstract fun onReady()

  override fun onCreate() {
    super.onCreate()
    initializeInjector()
  }

  override fun onDestroy() {
    sub.clear()
    destroyInjector()
    super.onDestroy()
  }

  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    onReady()
    return START_STICKY
  }

  override fun onBind(intent: Intent): IBinder? {
    return null
  }
}
