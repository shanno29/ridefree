package com.wiscosoft.ridefree.provider.api.entity.place

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.wiscosoft.ridefree.domain.place.PlaceNetwork
import com.wiscosoft.ridefree.domain.place.PlaceStorage
import com.wiscosoft.ridefree.provider.api.storage.StorageContainer
import retrofit2.Retrofit

val placeModule = Kodein.Module {

  bind<PlaceNetwork>() with singleton {
    val retrofit: Retrofit = instance()
    retrofit.create(PlaceNetwork::class.java)
  }

  bind<PlaceStorage>() with singleton {
    val storage: StorageContainer = instance()
    storage.placeStorage
  }

}
