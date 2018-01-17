package com.wiscosoft.ridefree.provider.redux

import com.wiscosoft.ridefree.core.app.debugLog
import dagger.Module
import dagger.Provides
import redux.api.Reducer
import redux.api.Store
import redux.api.enhancer.Middleware
import redux.applyMiddleware
import redux.createStore
import redux.logger.Logger
import redux.logger.createLoggerMiddleware
import javax.inject.Singleton

@Module
class ReduxModule {

  @Provides
  @Singleton
  fun state(): State {
   return State.DEFAULT
  }

  @Provides
  @Singleton
  fun reducer(): Reducer<State> {
    return Reducers.root
  }

  @Provides
  @Singleton
  fun middleware(): Middleware<State> {
    return createLoggerMiddleware(Logger(this::debugLog))
  }

  @Provides
  @Singleton
  fun store(reducers: Reducer<State>, state: State, logger: Middleware<State>): Store<State> {
    return createStore(reducers, state, applyMiddleware(logger))
  }

}
