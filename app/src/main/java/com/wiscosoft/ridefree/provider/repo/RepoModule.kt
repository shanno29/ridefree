package com.wiscosoft.ridefree.provider.repo

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.wiscosoft.ridefree.domain.payment.api.PaymentApi
import com.wiscosoft.ridefree.domain.place.api.PlaceApi
import com.wiscosoft.ridefree.domain.ride.api.RideApi
import com.wiscosoft.ridefree.domain.user.api.UserApi

class RepoModule {

  val bind = Kodein.Module {
    bind<Repo>() with singleton { repo(instance(), instance(), instance(), instance()) }
  }

  private fun repo(paymentApi: PaymentApi, placeApi: PlaceApi, rideApi: RideApi, userApi: UserApi): Repo {
    return RepoImp(paymentApi, placeApi, rideApi, userApi)
  }

}