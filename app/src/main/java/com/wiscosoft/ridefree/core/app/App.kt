package com.wiscosoft.ridefree.core.app

import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo
import com.wiscosoft.ridefree.core.base.BaseApp
import dagger.android.AndroidInjector

class App : BaseApp() {

  override val graph: AndroidInjector<out BaseApp> = DaggerAppComponent.builder().create(this)

  override fun onReady() {
    super.onReady()
    RxPaparazzo.register(this)
  }

}