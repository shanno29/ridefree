package com.wiscosoft.ridefree.domain.payment

import io.reactivex.Observable

class PaymentApiImp(storage: PaymentStorage) : PaymentApi {

  override val all: () -> Observable<List<Payment>> = {
    Observable
      .just("")
      .flatMap { storage.all().toObservable() }
  }

  override val get: (Int) -> Observable<Payment> = { id ->
    Observable
      .just(id)
      .flatMap { storage.get(id).toObservable() }
  }

  override val add: (Payment) -> Observable<Payment> = { payment ->
    Observable
      .just(storage.add(payment))
      .flatMap { storage.get(payment.id).toObservable() }
  }

  override val modify: (Payment) -> Observable<Payment> = { payment ->
    Observable
      .just(storage.modify(payment))
      .flatMap { storage.get(payment.id).toObservable() }
  }

  override val delete: (Payment) -> Observable<Payment> = { payment ->
    Observable
      .just(storage.delete(payment))
      .map { _ -> payment }
  }

}