package com.wiscosoft.ridefree.provider.redux

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import redux.api.Reducer
import redux.api.Store
import redux.api.enhancer.Middleware
import redux.applyMiddleware
import redux.createStore

val reduxModule = Kodein.Module {

  bind<State>() with singleton {
    State.default
  }

  bind<redux.api.Reducer<State>>() with singleton {
    Reducers.root
  }

  bind<Middleware<State>>() with singleton {
    Middlewares.logger
  }

  bind<Store<State>>() with singleton {
    val logger: Middleware<State> = instance()
    val reducers: Reducer<State> = instance()
    val state: State = instance()
    createStore(reducers, state, applyMiddleware(logger))
  }
}
