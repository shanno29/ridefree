package com.wiscosoft.ridefree.domain.payment.api

import com.wiscosoft.ridefree.domain.payment.Payment
import io.reactivex.Flowable

class PaymentApiImp(private val storage: PaymentStorage) : PaymentApi {

  override fun all(): Flowable<List<Payment>> =
    storage.all()

  override fun get(id: Int): Flowable<Payment> =
    storage.get(id)

  override fun add(payment: Payment): Flowable<Payment> =
    Flowable.just(storage.add(payment))
      .map { payment }

  override fun modify(payment: Payment): Flowable<Payment> =
    Flowable.just(storage.modify(payment))
      .map { payment }

  override fun delete(payment: Payment): Flowable<Payment> =
    Flowable.just(storage.delete(payment))
      .map { payment }

}