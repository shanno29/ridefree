package com.wiscosoft.ridefree.feature.settings

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.wiscosoft.ridefree.domain.user.UserApi
import com.wiscosoft.ridefree.provider.prefs.Prefs

class SettingsModule {

  val bind = Kodein.Module {
    bind<SettingsVM>() with provider { vm(instance(), instance()) }
  }

  private fun vm(userApi: UserApi, prefs: Prefs) = SettingsVM(userApi, prefs)

}