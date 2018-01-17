package com.wiscosoft.ridefree.feature.ui.settings

import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.domain.user.UserApi
import com.wiscosoft.ridefree.provider.redux.State
import com.wiscosoft.ridefree.provider.rx.setThreads
import io.reactivex.Observable
import redux.api.Store
import redux.asObservable

class SettingsVM(userApi: UserApi, store: Store<State>) {

  val userUpdates: Observable<User> =
    store
    .asObservable()
    .map { it.user }
    .setThreads()

}