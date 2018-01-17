package com.wiscosoft.ridefree.feature.ui.account.login

import com.wiscosoft.ridefree.domain.user.UserApi
import com.wiscosoft.ridefree.provider.redux.State
import dagger.Module
import dagger.Provides
import redux.api.Store

@Module
class LoginModule {


  @Provides
  fun loginVM(userApi: UserApi, store: Store<State>): LoginVM {
    return LoginVM(userApi, store)
  }

}