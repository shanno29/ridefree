package com.wiscosoft.ridefree.feature.login

import com.wiscosoft.ridefree.core.flowable
import com.wiscosoft.ridefree.core.setThreads
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.provider.api.entity.user.UserApi
import com.wiscosoft.ridefree.provider.redux.Action
import com.wiscosoft.ridefree.provider.redux.State
import io.reactivex.Flowable
import redux.api.Store
import redux.asObservable
import retrofit2.HttpException

class LoginVM(private val userApi: UserApi, private val store: Store<State>) {

  val userUpdate: Flowable<User> =
    store.asObservable()
      .flowable.map { it.user }
      .setThreads()

  fun login(user: User?): Flowable<User> {
    return userApi.logon(user ?: store.state.user)
      .doOnNext {
        store.dispatch(Action.AuthUpdate(true))
        //store.dispatch(Action.UserUpdate(it))
      }
      .setThreads()
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