package com.wiscosoft.ridefree.domain.ride.api

import com.wiscosoft.ridefree.domain.ride.Ride
import io.reactivex.Flowable

interface RideApi {

  fun all(params: Map<String, String>): Flowable<List<Ride>>

  fun get(id: Int): Flowable<Ride>

  fun add(params: Map<String, String>): Flowable<Map<String, String>>

  fun modify(ride: Ride): Flowable<Ride>

  fun delete(ride: Ride): Flowable<Ride>

}
