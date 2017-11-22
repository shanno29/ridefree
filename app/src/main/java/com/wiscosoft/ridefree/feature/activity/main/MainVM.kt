package com.wiscosoft.ridefree.feature.activity.main

import com.wiscosoft.ridefree.core.extensions.threads
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.provider.prefs.Prefs
import com.wiscosoft.ridefree.provider.repo.Repo
import io.reactivex.Flowable

class MainVM
constructor(private val repo: Repo, private val prefs: Prefs) {

  fun isLoggedIn(): Boolean = !prefs.cookies().get().isEmpty()

  fun user(): Flowable<User> = repo.userApi().get(prefs.owner().get()).compose(threads())

}
