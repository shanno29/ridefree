package com.wiscosoft.ridefree.provider.redux

/* Action */
sealed class Action {

  data class AuthUpdate(
    val flag: Boolean
  ) : Action()

  sealed class UserUpdate : Action() {

    data class Register(
      val email: String,
      val userName: String,
      val passWord: String
    ) : UserUpdate()

  }

  data class LocUpdate(
    val lat: Double,
    val lon: Double
  ) : Action()

}