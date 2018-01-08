package com.wiscosoft.ridefree.provider.redux

import com.wiscosoft.ridefree.domain.auth.Auth
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.provider.gps.Pos

data class State(
  val id: Int,
  val auth: Auth,
  val user: User,
  val pos: Pos
) {
  companion object {
    val DEFAULT = State(
      id = 0,
      auth = Auth.DEFAULT,
      user = User.DEFAULT,
      pos = Pos.DEFAULT
    )
  }
}