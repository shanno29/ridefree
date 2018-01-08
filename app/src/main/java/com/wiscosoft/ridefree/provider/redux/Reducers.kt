package com.wiscosoft.ridefree.provider.redux

import redux.api.Reducer
import redux.combineReducers

object Reducers {

  val root: Reducer<State> by lazy {
    combineReducers(
      authReducer,
      userReducer,
      positionReducer
    )
  }

  private val authReducer: Reducer<State> = Reducer { state, action ->
    when (action) {
      is Action.AuthUpdate ->
        state.copy(
          auth = state.auth.copy(
            flag = action.flag
          )
        )
      else -> state
    }
  }

  private val userReducer: Reducer<State> = Reducer { state, action ->
    when (action) {
      is Action.UserUpdate.Register ->
        state.copy(
          user = state.user.copy(
            email = action.email,
            userName = action.userName,
            passWord = action.passWord
          )
        )
      else -> state
    }
  }

  private val positionReducer: Reducer<State> = Reducer { state, action ->
    when (action) {
      is Action.LocUpdate ->
        state.copy(
          pos = state.pos.copy(
            lat = action.lat,
            lon = action.lon
          )
        )
      else -> state
    }
  }

}