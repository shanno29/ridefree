package com.wiscosoft.ridefree.feature.ui.account.register

import com.wiscosoft.ridefree.domain.user.UserApi
import com.wiscosoft.ridefree.provider.redux.State
import dagger.Module
import dagger.Provides
import redux.api.Store

@Module
class RegisterModule {

  @Provides
  fun registerVM(userApi: UserApi, store: Store<State>): RegisterVM {
    return RegisterVM(userApi, store)
  }

}