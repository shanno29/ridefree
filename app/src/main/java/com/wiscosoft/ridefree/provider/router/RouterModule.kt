package com.wiscosoft.ridefree.provider.router

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.singleton

class RouterModule {

  val bind = Kodein.Module {
    bind<Router>() with singleton { router() }
  }

  private fun router(): Router = RouterImp()

}

