package com.wiscosoft.ridefree.provider.redux

import android.util.Log
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import redux.api.Reducer
import redux.api.Store
import redux.api.enhancer.Middleware
import redux.applyMiddleware
import redux.createStore
import redux.logger.Logger
import redux.logger.createLoggerMiddleware

val reduxModule = Kodein.Module {

  bind<State>() with singleton {
    State.DEFAULT
  }

  bind<redux.api.Reducer<State>>() with singleton {
    Reducers.root
  }

  bind<Middleware<State>>() with singleton {
    createLoggerMiddleware(Logger<State> { Log.d("Redux", it.toString()) })
  }

  bind<Store<State>>() with singleton {
    val logger: Middleware<State> = instance()
    val reducers: Reducer<State> = instance()
    val state: State = instance()
    createStore(reducers, state, applyMiddleware(logger))
  }
}
