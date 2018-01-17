package com.wiscosoft.ridefree.domain.ride

import com.wiscosoft.ridefree.provider.storage.StorageContainer
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RideModule {

  @Provides
  @Singleton
  fun rideNetwork(retrofit: Retrofit): RideNetwork {
    return retrofit.create(RideNetwork::class.java)
  }

  @Provides
  @Singleton
  fun rideStorage(container: StorageContainer): RideStorage {
    return container.rideStorage
  }

  @Provides
  @Singleton
  fun rideApi(rideNetwork: RideNetwork, rideStorage: RideStorage): RideApi {
    return RideApiImp(rideStorage)
  }

}