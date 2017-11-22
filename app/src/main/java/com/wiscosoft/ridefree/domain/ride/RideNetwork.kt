package com.wiscosoft.ridefree.domain.ride

import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.POST

interface RideNetwork {

  @POST("RIDE/VIEW")
  fun all(@Body maxResults: Int, offsetResults: Int): Flowable<List<Ride>>

  @POST("RIDE/VIEW/{id}")
  fun get(@Body id: Int): Flowable<Ride>

  @POST("RIDE/REQUEST")
  fun add(@Body request: Ride): Flowable<Ride>

  @POST("RIDE/MODIFY")
  fun modify(@Body request: Ride): Flowable<Ride>

  @POST("RIDE/REMOVE")
  fun delete(ride: Ride): Flowable<Ride>

  @POST("RIDE/CLAIM")
  fun claim(@Body ride: Ride): Flowable<Ride>

}
