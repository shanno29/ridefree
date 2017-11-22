package com.wiscosoft.ridefree.domain.place

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.wiscosoft.ridefree.domain.place.api.PlaceApi
import com.wiscosoft.ridefree.domain.place.api.PlaceApiImp
import com.wiscosoft.ridefree.provider.storage.StorageContainer
import retrofit2.Retrofit

class PlaceModule {

  val bind = Kodein.Module {
    bind<PlaceNetwork>() with singleton { placeNetwork(instance()) }
    bind<PlaceStorage>() with singleton { placeStorage(instance()) }
    bind<PlaceApi>() with singleton { placeApi() }
  }

  private fun placeNetwork(retrofit: Retrofit): PlaceNetwork = retrofit.create(PlaceNetwork::class.java)

  private fun placeStorage(storageContainer: StorageContainer): PlaceStorage = storageContainer.placeStorage()

  private fun placeApi(): PlaceApi = PlaceApiImp()

}
