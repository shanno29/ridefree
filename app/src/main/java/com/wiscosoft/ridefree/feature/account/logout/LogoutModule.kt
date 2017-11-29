package com.wiscosoft.ridefree.feature.account.logout

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.wiscosoft.ridefree.domain.user.UserApi
import com.wiscosoft.ridefree.provider.prefs.Prefs

class LogoutModule {

  val bind = Kodein.Module {
    bind<LogoutVM>() with provider { vm(instance(), instance()) }
  }

  private fun vm(userApi: UserApi, prefs: Prefs) = LogoutVM(userApi, prefs)

}