package com.wiscosoft.ridefree.domain.ride

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface RideStorage {

  @Query("SELECT * FROM Ride")
  fun allRides(): Flowable<List<Ride>>

  @Query("SELECT * FROM Ride WHERE id = :id")
  fun getRide(id: Int): Flowable<Ride>

  @Insert(onConflict = REPLACE)
  fun addRide(ride: Ride): Long

  @Insert(onConflict = REPLACE)
  fun modifyRide(ride: Ride): Long

  @Delete
  fun deleteRide(ride: Ride): Int

}
