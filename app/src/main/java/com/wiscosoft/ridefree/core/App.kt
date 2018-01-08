package com.wiscosoft.ridefree.core

import android.app.Application
import android.content.Context
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.lazy
import com.github.salomonbrys.kodein.singleton
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo
import com.wiscosoft.ridefree.feature.featureModule
import com.wiscosoft.ridefree.provider.providerModule
import java.io.File

class App : Application(), KodeinAware {

  override val kodein by Kodein.lazy {
    import(providerModule)
    import(featureModule)

    bind<File>() with singleton {
      val context: Context = instance()
      context.cacheDir
    }

  }

  override fun onCreate() {
    super.onCreate()
    RxPaparazzo.register(this)
  }
}
