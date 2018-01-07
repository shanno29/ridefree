package com.wiscosoft.ridefree.provider.redux

import redux.api.Reducer

object Reducers {

  val root: Reducer<State> = Reducer { state, action ->
    when (action) {
      is Action.AuthUpdate -> state.copy(auth = action.auth)
      is Action.UserUpdate.Register -> state.copy(user = state.user.copy(email = action.email, userName = action.userName, passWord = action.passWord))
      is Action.LocUpdate -> state.copy(position = action.position)
      else -> state
    }
  }

}