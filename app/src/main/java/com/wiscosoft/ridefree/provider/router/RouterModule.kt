package com.wiscosoft.ridefree.provider.router

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.wiscosoft.ridefree.feature.account.login.LoginFragment

class RouterModule {

  val bind = Kodein.Module {
    bind<Router>() with singleton { router(instance()) }
  }

  private fun router(loginFragment: LoginFragment): Router = RouterImp(loginFragment)

}

