package com.wiscosoft.ridefree.domain.ride.api

import com.wiscosoft.ridefree.core.extensions.log
import com.wiscosoft.ridefree.domain.ride.Ride
import com.wiscosoft.ridefree.domain.ride.RideStorage
import io.reactivex.Flowable

class RideApiImp(private val rideStorage: RideStorage) : RideApi {

  override fun all(maxResults: Int, offsetResults: Int): Flowable<List<Ride>> {
    return rideStorage.allRides()
        .compose(log())
  }

  override fun get(id: Int): Flowable<Ride> {
    return rideStorage.getRide(id)
        .compose(log())
  }

  override fun add(ride: Ride): Flowable<Ride> {
    return Flowable.just(rideStorage.addRide(ride))
        .map { ride }
        .compose(log())
  }

  override fun modify(ride: Ride): Flowable<Ride> {
    return Flowable.just(rideStorage.modifyRide(ride))
        .map { ride }
        .compose(log())
  }

  override fun delete(ride: Ride): Flowable<Ride> {
    return Flowable.just(rideStorage.deleteRide(ride))
        .map { ride }
        .compose(log())
  }

}
