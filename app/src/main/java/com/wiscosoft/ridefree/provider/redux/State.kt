package com.wiscosoft.ridefree.provider.redux

import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.provider.gps.Position

data class State(
  val auth: Boolean,
  val user: User,
  val position: Position
) {
  companion object {
    val default = State(false, User.default, Position.default)
  }
}