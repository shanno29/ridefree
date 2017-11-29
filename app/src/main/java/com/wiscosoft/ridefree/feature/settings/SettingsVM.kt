package com.wiscosoft.ridefree.feature.settings

import com.wiscosoft.ridefree.core.extensions.threads
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.domain.user.UserApi
import com.wiscosoft.ridefree.provider.prefs.Prefs
import io.reactivex.Flowable

class SettingsVM
constructor(private val userApi: UserApi, val prefs: Prefs) {

  fun user(): Flowable<User> = userApi.get(prefs.owner().get()).compose(threads())

  fun networkInfo(): Set<String> = prefs.cookies().get()

  fun setUser(user: User): Flowable<User> = userApi.modify(user).compose(threads())

}
