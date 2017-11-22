package com.wiscosoft.ridefree.domain.place

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface PlaceStorage {

  @Query("SELECT * FROM Place")
  fun allPlaces(): Flowable<List<Place>>

  @Query("SELECT * FROM Place WHERE id = :id")
  fun getPlace(id: Int): Flowable<Place>

  @Insert(onConflict = REPLACE)
  fun addPlace(place: Place): Long

  @Insert(onConflict = REPLACE)
  fun modifyPlace(place: Place): Long

  @Delete
  fun removePlace(place: Place): Int

}
