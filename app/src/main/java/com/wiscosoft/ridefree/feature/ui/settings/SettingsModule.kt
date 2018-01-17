package com.wiscosoft.ridefree.feature.ui.settings

import com.wiscosoft.ridefree.domain.user.UserApi
import com.wiscosoft.ridefree.provider.redux.State
import dagger.Module
import dagger.Provides
import redux.api.Store

@Module
class SettingsModule {

  @Provides
  fun settingsVM(userApi: UserApi, store: Store<State>): SettingsVM {
    return SettingsVM(userApi, store)
  }

}