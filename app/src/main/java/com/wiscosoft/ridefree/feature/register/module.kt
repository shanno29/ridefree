package com.wiscosoft.ridefree.feature.register

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.wiscosoft.ridefree.provider.api.entity.user.UserApi

val registerModule = Kodein.Module {

  bind<RegisterVM>() with singleton {
    val userApi: UserApi = instance()
    RegisterVM(userApi)
  }

  bind<RegisterFragment>() with singleton {
    RegisterFragment()
  }

}
