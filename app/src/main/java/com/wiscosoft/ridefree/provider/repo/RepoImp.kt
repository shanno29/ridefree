package com.wiscosoft.ridefree.provider.repo

import com.wiscosoft.ridefree.domain.payment.api.PaymentApi
import com.wiscosoft.ridefree.domain.place.api.PlaceApi
import com.wiscosoft.ridefree.domain.ride.api.RideApi
import com.wiscosoft.ridefree.domain.user.api.UserApi

class RepoImp(private val paymentApi: PaymentApi, private val placeApi: PlaceApi, private val rideApi: RideApi, private val userApi: UserApi) : Repo {

  override fun paymentApi(): PaymentApi = paymentApi

  override fun placeApi(): PlaceApi = placeApi

  override fun rideApi(): RideApi = rideApi

  override fun userApi(): UserApi = userApi

}
