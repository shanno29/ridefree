package com.wiscosoft.ridefree.feature.account

import com.github.salomonbrys.kodein.Kodein
import com.wiscosoft.ridefree.feature.account.login.LoginModule
import com.wiscosoft.ridefree.feature.account.logout.LogoutModule
import com.wiscosoft.ridefree.feature.account.register.RegisterModule

class AccountModule {
  val bind = Kodein.Module {
    import(LoginModule().bind)
    import(LogoutModule().bind)
    import(RegisterModule().bind)
  }
}