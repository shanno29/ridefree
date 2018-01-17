package com.wiscosoft.ridefree.provider.redux

import com.wiscosoft.ridefree.domain.auth.Auth
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.provider.gps.Loc

data class State(
  val id: Int,
  val loc: Loc,
  val user: User,
  val auth: Auth
) {
  companion object {
    val DEFAULT = State(
      id = 0,
      loc = Loc.DEFAULT,
      auth = Auth.DEFAULT,
      user = User.DEFAULT
    )
  }
}