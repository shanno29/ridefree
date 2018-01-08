package com.wiscosoft.ridefree.provider.api

import com.github.salomonbrys.kodein.Kodein
import com.wiscosoft.ridefree.provider.api.network.networkModule
import com.wiscosoft.ridefree.provider.api.storage.storageModule
import com.wiscosoft.ridefree.provider.api.entity.payment.paymentModule
import com.wiscosoft.ridefree.provider.api.entity.place.placeModule
import com.wiscosoft.ridefree.provider.api.entity.ride.rideModule
import com.wiscosoft.ridefree.provider.api.entity.user.userModule

val apiModule = Kodein.Module {
  import(networkModule)
  import(storageModule)
  import(paymentModule)
  import(placeModule)
  import(rideModule)
  import(userModule)
}