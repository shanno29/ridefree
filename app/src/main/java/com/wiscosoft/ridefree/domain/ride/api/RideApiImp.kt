package com.wiscosoft.ridefree.domain.ride.api

import com.wiscosoft.ridefree.domain.ride.Ride
import io.reactivex.Flowable

class RideApiImp(private val rideNetwork: RideNetwork, private val rideStorage: RideStorage) : RideApi {

  override fun all(query: Map<String, Int>): Flowable<List<Ride>> =
    rideNetwork.all(query)
      .doOnNext { it.forEach(rideStorage::add) }

  override fun get(id: Int): Flowable<Ride> =
    rideStorage.get(id)

  override fun add(body: Map<String, String>): Flowable<Ride> =
    rideNetwork.add(body)

  override fun modify(ride: Ride): Flowable<Ride> =
    Flowable.just(rideStorage.modify(ride))
      .map { ride }

  override fun delete(ride: Ride): Flowable<Ride> =
    Flowable.just(rideStorage.delete(ride))
      .map { ride }
}