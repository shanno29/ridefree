package com.wiscosoft.ridefree.domain

import com.github.salomonbrys.kodein.Kodein
import com.wiscosoft.ridefree.domain.payment.paymentModule
import com.wiscosoft.ridefree.domain.place.placeModule
import com.wiscosoft.ridefree.domain.ride.rideModule
import com.wiscosoft.ridefree.domain.user.userModule

val domainModule = Kodein.Module {
  import(paymentModule)
  import(placeModule)
  import(rideModule)
  import(userModule)
}