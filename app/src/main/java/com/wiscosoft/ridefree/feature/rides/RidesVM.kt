package com.wiscosoft.ridefree.feature.rides

import com.wiscosoft.ridefree.core.base.EmptyListVM
import com.wiscosoft.ridefree.core.extensions.threads
import com.wiscosoft.ridefree.domain.ride.Ride
import com.wiscosoft.ridefree.domain.ride.RideApi
import io.reactivex.Flowable

class RidesVM
(private val rideApi: RideApi, val empty: EmptyListVM, var ride: Ride) {

  fun getRide(id: Int): Flowable<Ride> = rideApi.get(id).compose(threads())

  fun getAllRides(): Flowable<List<Ride>> {
    val params = mapOf(
      "maxResults" to "10",
      "offsetResults" to "0"
    )

    return rideApi
      .all(params)
      .compose(threads())
  }

  fun addRide(): Flowable<Ride> = rideApi.add(parameters(ride)).compose(threads())

  private fun parameters(ride: Ride): Map<String, String> {
    return mapOf(
        "pickUp" to ride.pickup,
        "restaurantId" to ride.restaurantId,
        "cCNumber" to "4111111111111111",
        "cVV2Number" to "123",
        "cCExpiration" to "10/1/2018"
    )
  }

  fun deleteRide(id: Int): Flowable<Ride> = rideApi.get(id).flatMap(rideApi::delete).compose(threads())

}
