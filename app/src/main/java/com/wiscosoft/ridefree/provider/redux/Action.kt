package com.wiscosoft.ridefree.provider.redux

/* Action */
sealed class Action {

  data class LocUpdate(
    val lat: Double,
    val lon: Double
  ) : Action()

  data class AuthUpdate(
    val flag: Boolean
  ) : Action()

  sealed class UserUpdate : Action() {

    data class Register(
      val email: String,
      val username: String,
      val password: String
    ) : UserUpdate()

    data class Login(
      val username: String,
      val password: String
    ) : UserUpdate()

  }

}