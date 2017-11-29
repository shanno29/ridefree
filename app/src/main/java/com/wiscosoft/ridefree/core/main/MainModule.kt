package com.wiscosoft.ridefree.core.main

import android.content.Context
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.mikepenz.aboutlibraries.LibsBuilder
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IItem
import com.wiscosoft.ridefree.R
import com.wiscosoft.ridefree.R.style.RideFreeTheme
import com.wiscosoft.ridefree.core.view.DrawerToggle
import com.wiscosoft.ridefree.domain.user.UserApi
import com.wiscosoft.ridefree.provider.prefs.Prefs

class MainModule {

  val bind = Kodein.Module {
    bind<DrawerToggle>() with singleton { drawerToggle(instance()) }
    bind<MainVM>() with singleton { vm(instance(), instance()) }
    bind<FastAdapter<IItem<*, *>>>() with singleton { libsAdapter(instance()) }
  }

  private fun drawerToggle(activity: MainActivity): DrawerToggle {
    return DrawerToggle(activity, R.string.drawer_close, R.string.drawer_open)
  }

  private fun vm(userApi: UserApi, prefs: Prefs): MainVM {
    return MainVM(userApi, prefs)
  }

  private fun libsAdapter(context: Context): FastAdapter<IItem<*, *>> {
    return LibsBuilder()
      .withAboutDescription("Created By Matthew Shannon")
      .withAboutAppName("RideFree 2017")
      .withActivityTheme(RideFreeTheme)
      .withVersionShown(true)
      .withLicenseShown(true)
      .withAutoDetect(true)
      .adapter(context)
  }

}
