package com.wiscosoft.ridefree.feature.ui.account.logout

import com.wiscosoft.ridefree.domain.user.UserApi
import com.wiscosoft.ridefree.provider.redux.State
import dagger.Module
import dagger.Provides
import redux.api.Store

@Module
class LogoutModule {

  @Provides
  fun logoutVM(userApi: UserApi, store: Store<State>): LogoutVM {
    return LogoutVM(userApi, store)
  }

}