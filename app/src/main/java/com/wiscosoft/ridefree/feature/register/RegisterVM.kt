package com.wiscosoft.ridefree.feature.register

import com.wiscosoft.ridefree.core.setThreads
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.provider.api.entity.user.UserApi
import io.reactivex.Flowable
import retrofit2.HttpException

class RegisterVM(private val userApi: UserApi) {

  fun register(user: User): Flowable<User> =
    userApi.logon(user)
      .setThreads()

  fun createUser(email: String, username: String, password: String): User {
    return User.DEFAULT.copy(
      email = email,
      userName = username,
      passWord = password
    )
  }

  fun getReason(error: Throwable): String {
    return if (error !is HttpException) error.localizedMessage
    else when (error.code()) {
      409 -> "This Username Already Exists"
      452 -> "Password Entered Is Too Weak"
      503 -> "Server Error Try Again Later"
      else -> "Unidentified Error"
    }
  }

}