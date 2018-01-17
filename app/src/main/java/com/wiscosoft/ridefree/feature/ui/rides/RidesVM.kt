package com.wiscosoft.ridefree.feature.ui.rides

import com.wiscosoft.ridefree.domain.payment.Payment
import com.wiscosoft.ridefree.domain.ride.Ride
import com.wiscosoft.ridefree.domain.ride.RideApi
import com.wiscosoft.ridefree.provider.redux.State
import com.wiscosoft.ridefree.provider.rx.setThreads
import io.reactivex.Observable
import redux.api.Store

class RidesVM(private val rideApi: RideApi, store: Store<State>) {

  val deleteRide: (Int) -> Observable<Ride> = { getRide(it).flatMap { rideApi.delete(it) }.setThreads() }

  val getRide: (Int) -> Observable<Ride> = { rideApi.get(it).setThreads() }

  fun getAllRides(max: Int, offset: Int): Observable<List<Ride>> {
    return rideApi
      .all(mapOf(
        "maxResults" to max,
        "offsetResults" to offset
      ))
      .setThreads()
  }

  fun addRide(ride: Ride, payment: Payment): Observable<Ride> {
    return rideApi
      .add(mapOf(
        "pickUp" to ride.pickup,
        "restaurantId" to ride.restaurantId,
        "cCNumber" to payment.cCNumber,
        "cVV2Number" to payment.cVV2Number,
        "cCExpiration" to payment.cCExpiration
      ))
      .setThreads()
  }
}