package com.wiscosoft.ridefree.domain.place

import com.wiscosoft.ridefree.provider.storage.StorageContainer
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class PlaceModule {

  @Provides
  @Singleton
  fun placeNetwork(retrofit: Retrofit): PlaceNetwork  {
    return retrofit.create(PlaceNetwork::class.java)
  }

  @Provides
  @Singleton
  fun placeStorage(container: StorageContainer): PlaceStorage {
    return container.placeStorage
  }

}