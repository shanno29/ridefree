package com.wiscosoft.ridefree.feature.ui.account.logout

import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.domain.user.UserApi
import com.wiscosoft.ridefree.provider.redux.State
import com.wiscosoft.ridefree.provider.rx.setThreads
import io.reactivex.Observable
import redux.api.Store
import retrofit2.HttpException

class LogoutVM(userApi: UserApi, store: Store<State>) {

  val logout: () -> Observable<User> = {
    userApi.logoff(store.state.user).setThreads()
  }

  val reason: (Throwable) -> String = {
    if (it !is HttpException) it.localizedMessage
    else when (it.code()) {
      503 -> "Server Error Try Again Later"
      else -> "Unidentified Error: "
    }
  }

}