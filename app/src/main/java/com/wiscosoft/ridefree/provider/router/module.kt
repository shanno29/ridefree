package com.wiscosoft.ridefree.provider.router

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.singleton

val routerModule = Kodein.Module {
  bind<Router>() with singleton {
    RouterImpl()
  }
}