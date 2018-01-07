package com.wiscosoft.ridefree.domain.ride.api

import com.wiscosoft.ridefree.domain.ride.Ride
import io.reactivex.Flowable

interface RideApi {

  fun all(query: Map<String, Int>): Flowable<List<Ride>>

  fun get(id: Int): Flowable<Ride>

  fun add(body: Map<String, String>): Flowable<Ride>

  fun modify(ride: Ride): Flowable<Ride>

  fun delete(ride: Ride): Flowable<Ride>
}