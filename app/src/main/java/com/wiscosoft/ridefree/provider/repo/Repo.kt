package com.wiscosoft.ridefree.provider.repo

import com.wiscosoft.ridefree.domain.payment.api.PaymentApi
import com.wiscosoft.ridefree.domain.place.api.PlaceApi
import com.wiscosoft.ridefree.domain.ride.api.RideApi
import com.wiscosoft.ridefree.domain.user.api.UserApi

interface Repo {

  fun paymentApi(): PaymentApi

  fun placeApi(): PlaceApi

  fun rideApi(): RideApi

  fun userApi(): UserApi

}
