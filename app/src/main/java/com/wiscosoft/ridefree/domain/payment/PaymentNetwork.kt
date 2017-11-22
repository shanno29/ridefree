package com.wiscosoft.ridefree.domain.payment

import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.POST

interface PaymentNetwork {

  @POST("PAYMENT/VIEW")
  fun allPayments(): Flowable<List<Payment>>

  @POST("PAYMENT/VIEW/{id}")
  fun getPayment(@Body id: Int): Flowable<Payment>

  @POST("PAYMENT/ADD")
  fun addPayment(payment: Payment): Flowable<Payment>

  @POST("PAYMENT/MODIFY")
  fun modifyPayment(payment: Payment): Flowable<Payment>

  @POST("PAYMENT/REMOVE")
  fun deletePayment(payment: Payment): Flowable<Payment>

}
