package com.wiscosoft.ridefree.domain.ride

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface RideNetwork {

  @POST("RIDE/VIEW")
  fun all(@QueryMap queryMap: Map<String, Int>): Observable<List<Ride>>

  @POST("RIDE/VIEW/{id}")
  fun get(@Body id: Int): Observable<Ride>

  @POST("RIDE/REQUEST")
  fun add(@Body body: Map<String, String>): Observable<Ride>

  @POST("RIDE/MODIFY")
  fun modify(@Body request: Ride): Observable<Ride>

  @POST("RIDE/REMOVE")
  fun delete(ride: Ride): Observable<Ride>

  @POST("RIDE/CLAIM")
  fun claim(@Body ride: Ride): Observable<Ride>
}