package com.wiscosoft.ridefree.domain.payment

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface PaymentStorage {

  @Query("SELECT * FROM Payment")
  fun allPayments(): Flowable<List<Payment>>

  @Query("SELECT * FROM Payment WHERE id = :id")
  fun getPayment(id: Int): Flowable<Payment>

  @Insert(onConflict = REPLACE)
  fun addPayment(payment: Payment): Long

  @Insert(onConflict = REPLACE)
  fun modifyPayment(payment: Payment): Long

  @Delete
  fun deletePayment(payment: Payment): Int

}
