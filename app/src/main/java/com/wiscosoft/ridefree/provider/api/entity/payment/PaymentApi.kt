package com.wiscosoft.ridefree.provider.api.entity.payment

import com.wiscosoft.ridefree.domain.payment.Payment
import io.reactivex.Flowable

interface PaymentApi {

  fun all(): Flowable<List<Payment>>

  fun get(id: Int): Flowable<Payment>

  fun add(payment: Payment): Flowable<Payment>

  fun modify(payment: Payment): Flowable<Payment>

  fun delete(payment: Payment): Flowable<Payment>
}
