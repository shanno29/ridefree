package com.wiscosoft.ridefree.feature.notifications

import com.wiscosoft.ridefree.provider.gps.Gps
import com.wiscosoft.ridefree.provider.push.Push
import com.wiscosoft.ridefree.provider.redux.State
import dagger.Module
import dagger.Provides
import redux.api.Store

@Module
class NotificationModule {

  @Provides
  fun notificationVM(gps: Gps, push: Push, store: Store<State>): NotificationVM {
    return NotificationVM(gps, push, store)
  }

}