package com.wiscosoft.ridefree.provider.redux

import redux.api.Reducer

object Reducers {

  val root: Reducer<State> = Reducer { state, action ->
    when (action) {

      is Action.AuthUpdate -> state.copy(
          auth = state.auth.copy(
          flag = action.flag
        )
      )

      is Action.LocUpdate -> state.copy(
        loc = state.loc.copy(
          lat = action.lat,
          lon = action.lon
        )
      )

      is Action.UserUpdate -> when(action) {
        is Action.UserUpdate.Register ->
          state.copy(
          user = state.user.copy(
            email = action.email,
            userName = action.username,
            passWord = action.password
          )
        )
        is Action.UserUpdate.Login -> state.copy(
          user = state.user.copy(
            userName = action.username,
            passWord = action.password
          )
        )
      }

      else -> state
    }
  }

}