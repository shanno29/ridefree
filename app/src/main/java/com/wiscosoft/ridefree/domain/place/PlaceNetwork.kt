package com.wiscosoft.ridefree.domain.place

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface PlaceNetwork {

  @POST("PLACE/VIEW")
  fun allPlaces(): Observable<List<Place>>

  @POST("PLACE/VIEW/{id}")
  fun getPlace(@Body id: Int): Observable<Place>

  @POST("PLACE/ADD")
  fun addPlace(place: Place): Observable<Place>

  @POST("PLACE/MODIFY")
  fun modifyPlace(place: Place): Observable<Place>

  @POST("PLACE/REMOVE")
  fun deletePlace(place: Place): Observable<Place>
}