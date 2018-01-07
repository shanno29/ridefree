package com.wiscosoft.ridefree.provider.redux

import com.wiscosoft.ridefree.provider.gps.Position

/* Action */
sealed class Action {

  data class AuthUpdate(val auth: Boolean) : Action()

  sealed class UserUpdate : Action() {
    data class Register(val email: String, val userName: String, val passWord: String) : Action()
  }

  data class LocUpdate(val position: Position) : Action()
}