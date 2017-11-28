package com.wiscosoft.ridefree.feature.fragment.account.register

import com.wiscosoft.ridefree.core.extensions.threads
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.provider.repo.Repo
import io.reactivex.Flowable
import retrofit2.HttpException

class RegisterVM
constructor(private val repo: Repo, var user: User) {

  fun register(): Flowable<User> {
    return repo.userApi()
      .register(user)
      .compose(threads())
  }

  fun getReason(error: Throwable): String {
    if (error !is HttpException) return error.localizedMessage
    return when (error.code()) {
      409 -> "This Username Already Exists"
      452 -> "Password Entered Is Too Weak"
      503 -> "Server Error Try Again Later"
      else -> "Unidentified Error: "
    }
  }

}
