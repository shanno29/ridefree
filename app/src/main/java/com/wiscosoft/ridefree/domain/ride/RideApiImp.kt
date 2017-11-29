package com.wiscosoft.ridefree.domain.ride

import com.wiscosoft.ridefree.core.extensions.log
import io.reactivex.Flowable

class RideApiImp(private val rideNetwork: RideNetwork, private val rideStorage: RideStorage) : RideApi {

  override fun all(queryMap: Map<String, String>): Flowable<List<Ride>> {
    return rideNetwork.all(queryMap)
      .doOnNext { it.forEach { rideStorage.addRide(it) } }
      .compose(log())
  }

  override fun get(id: Int): Flowable<Ride> {
    return rideStorage.getRide(id)
      .compose(log())
  }

  override fun add(body: Map<String, String>): Flowable<Ride> {
    return rideNetwork.add(body)
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
