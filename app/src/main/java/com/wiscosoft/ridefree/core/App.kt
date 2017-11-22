package com.wiscosoft.ridefree.core

import android.app.Application
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.lazy
import com.github.salomonbrys.kodein.with
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo
import com.wiscosoft.ridefree.domain.DomainModule
import com.wiscosoft.ridefree.feature.FeatureModule
import com.wiscosoft.ridefree.provider.ProviderModule

class App : Application(), KodeinAware {

  override val kodein by Kodein.lazy {
    constant("api") with "https://wiscosoft.co:27010/API/"
    constant("db") with "data.db"
    import(ProviderModule().bind)
    import(DomainModule().bind)
    import(FeatureModule().bind)
  }

  override fun onCreate() {
    super.onCreate()
    readyUp()
  }

  private fun readyUp() {
    RxPaparazzo.register(this)
  }

}