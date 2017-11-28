package com.wiscosoft.ridefree.feature.fragment.settings

import com.wiscosoft.ridefree.core.extensions.threads
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.provider.prefs.Prefs
import com.wiscosoft.ridefree.provider.repo.Repo
import io.reactivex.Flowable

class SettingsVM
constructor(private val repo: Repo, val prefs: Prefs) {

  fun user(): Flowable<User> = repo.userApi().get(prefs.owner().get()).compose(threads())

  fun networkInfo(): Set<String> = prefs.cookies().get()

  fun setUser(user: User): Flowable<User> {
    return repo.userApi().modify(user)
      .compose(threads())
  }

}
