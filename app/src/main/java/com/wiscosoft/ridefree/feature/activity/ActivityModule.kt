package com.wiscosoft.ridefree.feature.activity

import android.content.Context
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.mikepenz.aboutlibraries.LibsBuilder
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IItem
import com.wiscosoft.ridefree.R
import com.wiscosoft.ridefree.feature.activity.main.MainModule
import com.wiscosoft.ridefree.feature.activity.splash.SplashModule

class ActivityModule {
  val bind = Kodein.Module {
    import(SplashModule().bind)
    import(MainModule().bind)
    bind() from singleton { libsAdapter(instance()) }

  }

  private fun libsAdapter(context: Context): FastAdapter<IItem<*, *>> {
    return LibsBuilder()
        .withAboutDescription("Created By Matthew Shannon")
        .withAboutAppName("RideFree 2017")
        .withActivityTheme(R.style.RideFreeTheme)
        .withVersionShown(true)
        .withLicenseShown(true)
        .withAutoDetect(true)
        .adapter(context)
  }

}
