package com.wiscosoft.ridefree.feature.account.logout

import com.wiscosoft.ridefree.core.extensions.threads
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.domain.user.UserApi
import com.wiscosoft.ridefree.provider.prefs.Prefs
import io.reactivex.Flowable
import retrofit2.HttpException

class LogoutVM
constructor(private val userApi: UserApi, private val prefs: Prefs) {

  fun logout(): Flowable<User> {
    return userApi.get(prefs.owner().get())
      .flatMap(userApi::logoff)
      .doOnNext { prefs.clearAll() }
      .compose(threads())
  }

  fun getReason(error: Throwable): String {
    if (error !is HttpException) return error.localizedMessage
    return when (error.code()) {
      503 -> "Server Error Try Again Later"
      else -> "Unidentified Error: "
    }
  }

}
