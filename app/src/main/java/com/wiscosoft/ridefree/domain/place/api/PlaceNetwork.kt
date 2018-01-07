package com.wiscosoft.ridefree.domain.place.api

import com.wiscosoft.ridefree.domain.place.Place
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.POST

interface PlaceNetwork {

  @POST("PLACE/VIEW")
  fun allPlaces(): Flowable<List<Place>>

  @POST("PLACE/VIEW/{id}")
  fun getPlace(@Body id: Int): Flowable<Place>

  @POST("PLACE/ADD")
  fun addPlace(place: Place): Flowable<Place>

  @POST("PLACE/MODIFY")
  fun modifyPlace(place: Place): Flowable<Place>

  @POST("PLACE/REMOVE")
  fun deletePlace(place: Place): Flowable<Place>
}