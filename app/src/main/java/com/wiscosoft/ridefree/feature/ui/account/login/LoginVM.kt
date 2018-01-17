package com.wiscosoft.ridefree.feature.ui.account.login

import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.domain.user.UserApi
import com.wiscosoft.ridefree.provider.redux.Action
import com.wiscosoft.ridefree.provider.redux.State
import com.wiscosoft.ridefree.provider.rx.setThreads
import io.reactivex.Observable
import redux.api.Store
import retrofit2.HttpException

class LoginVM (userApi: UserApi, store: Store<State>) {

  val login: (String, String) -> Observable<User> = { username, password ->
    store.dispatch(Action.UserUpdate.Login(username, password))
    userApi.logon(store.state.user).setThreads()
  }

  val reason: (Throwable) -> String = {
    if (it !is HttpException) it.localizedMessage
    else when (it.code()) {
      414 -> "Too Many Clients"
      401 -> "Username / Password Incorrect"
      501 -> "Server Error Try Again Later"
      else -> "Unidentified Error"
    }
  }

}