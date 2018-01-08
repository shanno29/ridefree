package com.wiscosoft.ridefree.domain.payment

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.wiscosoft.ridefree.core.base.BaseDao
import io.reactivex.Flowable

@Dao
interface PaymentStorage : BaseDao<Payment> {

  @Query("SELECT * FROM Payment")
  fun all(): Flowable<List<Payment>>

  @Query("SELECT * FROM Payment WHERE id = :id")
  fun get(id: Int): Flowable<Payment>
}