package com.wiscosoft.ridefree.feature.ui.rides

import com.wiscosoft.ridefree.domain.ride.RideApi
import com.wiscosoft.ridefree.provider.redux.State
import dagger.Module
import dagger.Provides
import redux.api.Store

@Module
class RidesModule {

  @Provides
  fun ridesVm(rideApi: RideApi, store: Store<State>): RidesVM {
    return RidesVM(rideApi, store)
  }

}