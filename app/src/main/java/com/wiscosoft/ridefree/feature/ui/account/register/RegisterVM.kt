package com.wiscosoft.ridefree.feature.ui.account.register

import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.domain.user.UserApi
import com.wiscosoft.ridefree.provider.redux.Action
import com.wiscosoft.ridefree.provider.redux.State
import com.wiscosoft.ridefree.provider.rx.setThreads
import io.reactivex.Observable
import redux.api.Store
import retrofit2.HttpException

class RegisterVM (userApi: UserApi, store: Store<State>) {

  val register: (String, String, String) -> Observable<User> = { email, username, password ->
    store.dispatch(Action.UserUpdate.Register(email, username, password))
    userApi.logon(store.state.user).setThreads()
  }

  val reason: (Throwable) -> String = {
    if (it !is HttpException) it.localizedMessage
    else when (it.code()) {
      409 -> "This Username Already Exists"
      452 -> "Password Entered Is Too Weak"
      503 -> "Server Error Try Again Later"
      else -> "Unidentified Error"
    }
  }

}