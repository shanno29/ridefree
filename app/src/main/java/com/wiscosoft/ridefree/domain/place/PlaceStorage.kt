package com.wiscosoft.ridefree.domain.place

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.wiscosoft.ridefree.core.base.BaseDao
import io.reactivex.Flowable

@Dao
interface PlaceStorage : BaseDao<Place> {

  @Query("SELECT * FROM Place")
  fun all(): Flowable<List<Place>>

  @Query("SELECT * FROM Place WHERE id = :id")
  fun get(id: Int): Flowable<Place>
}