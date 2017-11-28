package com.wiscosoft.ridefree.feature.fragment.account.logout

import com.wiscosoft.ridefree.core.extensions.threads
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.provider.prefs.Prefs
import com.wiscosoft.ridefree.provider.repo.Repo
import io.reactivex.Flowable
import retrofit2.HttpException

class LogoutVM
constructor(private val repo: Repo, private val prefs: Prefs) {

  fun logout(): Flowable<User> {
    return repo.userApi().get(prefs.owner().get())
      .flatMap { repo.userApi().logoff(it) }
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
