package com.wiscosoft.ridefree.feature.splash

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.wiscosoft.ridefree.provider.permission.Permissions

class SplashModule {

  val bind = Kodein.Module {
    bind<SplashVM>() with singleton { vm(instance()) }
  }

  private fun vm(permissions: Permissions): SplashVM = SplashVM(permissions)

}