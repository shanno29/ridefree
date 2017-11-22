package com.wiscosoft.ridefree.domain.ride.api

import com.wiscosoft.ridefree.domain.ride.Ride
import io.reactivex.Flowable

interface RideApi {

  fun all(maxResults: Int, offsetResults: Int): Flowable<List<Ride>>

  fun get(id: Int): Flowable<Ride>

  fun add(ride: Ride): Flowable<Ride>

  fun modify(ride: Ride): Flowable<Ride>

  fun delete(ride: Ride): Flowable<Ride>

}
