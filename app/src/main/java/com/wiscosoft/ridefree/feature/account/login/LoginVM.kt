package com.wiscosoft.ridefree.feature.account.login

import com.wiscosoft.ridefree.core.extensions.threads
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.domain.user.UserApi
import com.wiscosoft.ridefree.provider.prefs.Prefs
import io.reactivex.Flowable
import retrofit2.HttpException

class LoginVM
constructor(private val userApi: UserApi, private val prefs: Prefs, var user: User) {

  fun login(): Flowable<User> {
    return userApi.logon(user)
      .doOnNext { prefs.owner().set(it.id) }
      .compose(threads())
  }

  fun getReason(error: Throwable): String {
    if (error !is HttpException) return error.localizedMessage
    return when (error.code()) {
      414 -> "Too Many Clients"
      401 -> "Username / Password Incorrect"
      501 -> "Oops Something Went Wrong, Try Again Later"
      else -> "Unidentified Error: "
    }
  }

}
