package com.wiscosoft.ridefree.domain.ride.api

import com.wiscosoft.ridefree.core.extensions.log
import com.wiscosoft.ridefree.domain.ride.Ride
import com.wiscosoft.ridefree.domain.ride.RideNetwork
import com.wiscosoft.ridefree.domain.ride.RideStorage
import io.reactivex.Flowable

class RideApiImp(private val rideNetwork: RideNetwork, private val rideStorage: RideStorage) : RideApi {

  override fun all(params: Map<String, String>): Flowable<List<Ride>> {
    return rideNetwork.all(params)
      .doOnNext { it.forEach { rideStorage.addRide(it) } }
      .compose(log())
  }

  override fun get(id: Int): Flowable<Ride> {
    return rideStorage.getRide(id)
      .compose(log())
  }

  override fun add(params: Map<String, String>): Flowable<Map<String, String>> {
    return rideNetwork.add(params)
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
