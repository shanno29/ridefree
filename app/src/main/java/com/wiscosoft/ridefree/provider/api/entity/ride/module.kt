package com.wiscosoft.ridefree.provider.api.entity.ride

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.wiscosoft.ridefree.domain.ride.RideNetwork
import com.wiscosoft.ridefree.domain.ride.RideStorage
import com.wiscosoft.ridefree.provider.api.storage.StorageContainer
import retrofit2.Retrofit

val rideModule = Kodein.Module {

  bind<RideNetwork>() with singleton {
    val retrofit: Retrofit = instance()
    retrofit.create(RideNetwork::class.java)
  }

  bind<RideStorage>() with singleton {
    val storage: StorageContainer = instance()
    storage.rideStorage
  }

  bind<RideApi>() with singleton {
    val network: RideNetwork = instance()
    val storage: RideStorage = instance()
    RideApiImp(network, storage)
  }
}
