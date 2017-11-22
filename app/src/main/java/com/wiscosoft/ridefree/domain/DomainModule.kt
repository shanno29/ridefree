package com.wiscosoft.ridefree.domain

import com.github.salomonbrys.kodein.Kodein
import com.wiscosoft.ridefree.domain.payment.PaymentModule
import com.wiscosoft.ridefree.domain.place.PlaceModule
import com.wiscosoft.ridefree.domain.ride.RideModule
import com.wiscosoft.ridefree.domain.user.UserModule

class DomainModule {
  val bind = Kodein.Module {
    import(PaymentModule().bind)
    import(PlaceModule().bind)
    import(RideModule().bind)
    import(UserModule().bind)
  }
}