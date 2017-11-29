package com.wiscosoft.ridefree.domain.ride

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.wiscosoft.ridefree.provider.storage.StorageContainer
import retrofit2.Retrofit

class RideModule {

  val bind = Kodein.Module {
    bind<RideNetwork>() with singleton { rideNetwork(instance()) }
    bind<RideStorage>() with singleton { rideStorage(instance()) }
    bind<RideApi>() with singleton { rideApi(instance(), instance()) }
  }

  private fun rideNetwork(retrofit: Retrofit): RideNetwork = retrofit.create(RideNetwork::class.java)

  private fun rideStorage(storageContainer: StorageContainer): RideStorage = storageContainer.rideStorage()

  private fun rideApi(network: RideNetwork, storage: RideStorage): RideApi = RideApiImp(network, storage)

}
