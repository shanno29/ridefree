package com.wiscosoft.ridefree.domain.ride

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.wiscosoft.ridefree.core.base.BaseDao
import io.reactivex.Flowable

@Dao
interface RideStorage : BaseDao<Ride> {

  @Query("SELECT * FROM Ride")
  fun all(): Flowable<List<Ride>>

  @Query("SELECT * FROM Ride WHERE id = :id")
  fun get(id: Int): Flowable<Ride>
}