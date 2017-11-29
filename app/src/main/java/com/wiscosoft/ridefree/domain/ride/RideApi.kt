package com.wiscosoft.ridefree.domain.ride

import com.wiscosoft.ridefree.domain.ride.Ride
import io.reactivex.Flowable

interface RideApi {

  fun all(queryMap: Map<String, String>): Flowable<List<Ride>>

  fun get(id: Int): Flowable<Ride>

  fun add(body: Map<String, String>): Flowable<Ride>

  fun modify(ride: Ride): Flowable<Ride>

  fun delete(ride: Ride): Flowable<Ride>

}
