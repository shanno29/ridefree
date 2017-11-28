package com.wiscosoft.ridefree.feature.fragment.rides

import com.wiscosoft.ridefree.core.base.EmptyListVM
import com.wiscosoft.ridefree.core.extensions.threads
import com.wiscosoft.ridefree.domain.payment.Payment
import com.wiscosoft.ridefree.domain.ride.Ride
import com.wiscosoft.ridefree.provider.repo.Repo
import io.reactivex.Flowable

class RidesVM
(private val repo: Repo, val empty: EmptyListVM, var ride: Ride) {

  fun getRide(id: Int): Flowable<Ride> = repo.rideApi().get(id).compose(threads())

  fun getAllRides(): Flowable<List<Ride>> {
    val params = mapOf(
      "maxResults" to "10",
      "offsetResults" to "0"
    )

    return repo.rideApi()
      .all(params)
      .compose(threads())
  }

  fun addRide(): Flowable<Map<String, String>> {
    return repo.paymentApi().all()
      .flatMap { repo.rideApi().add(requestRideParams(ride, it.first())) }
      .compose(threads())
  }

  private fun requestRideParams(ride: Ride, payment: Payment): Map<String, String> {
    return mapOf(
        "pickUp" to ride.pickup,
        "restaurantId" to ride.restaurantId,
        "cCNumber" to payment.cCNumber,
        "cVV2Number" to payment.cVV2Number,
        "cCExpiration" to payment.cCExpiration
    )
  }

  fun deleteRide(id: Int): Flowable<Ride> {
    return repo.rideApi().get(id)
      .flatMap { repo.rideApi().delete(it) }
      .compose(threads())
  }

}
