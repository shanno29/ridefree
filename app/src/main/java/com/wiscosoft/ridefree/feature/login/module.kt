package com.wiscosoft.ridefree.feature.login

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.wiscosoft.ridefree.provider.api.entity.user.UserApi
import com.wiscosoft.ridefree.provider.redux.State
import redux.api.Store

val loginModule = Kodein.Module {

  bind<LoginVM>() with singleton {
    val userApi: UserApi = instance()
    val store: Store<State> = instance()
    LoginVM(userApi, store)
  }

  bind<LoginFragment>() with singleton {
    LoginFragment()
  }

}