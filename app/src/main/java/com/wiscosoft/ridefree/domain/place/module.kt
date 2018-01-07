package com.wiscosoft.ridefree.domain.place

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.wiscosoft.ridefree.domain.place.api.PlaceNetwork
import com.wiscosoft.ridefree.domain.place.api.PlaceStorage
import com.wiscosoft.ridefree.provider.storage.StorageContainer
import retrofit2.Retrofit

val placeModule = Kodein.Module {

  bind<PlaceNetwork>() with singleton {
    val retrofit: Retrofit = instance()
    retrofit.create(PlaceNetwork::class.java)
  }

  bind<PlaceStorage>() with singleton {
    val storage: StorageContainer = instance()
    storage.placeStorage()
  }

}
