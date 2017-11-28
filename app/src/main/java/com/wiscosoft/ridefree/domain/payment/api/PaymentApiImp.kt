package com.wiscosoft.ridefree.domain.payment.api

import com.wiscosoft.ridefree.core.extensions.log
import com.wiscosoft.ridefree.domain.payment.Payment
import com.wiscosoft.ridefree.domain.payment.PaymentStorage
import io.reactivex.Flowable
import io.reactivex.Flowable.just

class PaymentApiImp
constructor(private val storage: PaymentStorage) : PaymentApi {

  override fun all(): Flowable<List<Payment>> {
    return storage.allPayments()
      .compose(log())
  }

  override fun get(id: Int): Flowable<Payment> {
    return storage.getPayment(id)
      .compose(log())
  }

  override fun add(payment: Payment): Flowable<Payment> {
    return just(storage.addPayment(payment))
      .map { payment }
      .compose(log())
  }

  override fun modify(payment: Payment): Flowable<Payment> {
    return just(storage.modifyPayment(payment))
      .map { payment }
      .compose(log())
  }

  override fun delete(payment: Payment): Flowable<Payment> {
    return just(storage.deletePayment(payment))
      .map { payment }
      .compose(log())
  }

}

