package com.wiscosoft.ridefree.domain.ride

import io.reactivex.Observable

interface RideApi {

  val all: (Map<String, Int>) -> Observable<List<Ride>>

  val get: (Int) -> Observable<Ride>

  val add: (Map<String, String>) -> Observable<Ride>

  val modify: (Ride) -> Observable<Ride>

  val delete: (Ride) -> Observable<Ride>

}