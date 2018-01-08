package com.wiscosoft.ridefree.domain.auth

data class Auth(
  val flag: Boolean
) {
  companion object {
    val DEFAULT = Auth(
      flag = false
    )
  }
}
