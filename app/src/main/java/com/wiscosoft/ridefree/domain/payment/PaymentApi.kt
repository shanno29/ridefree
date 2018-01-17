package com.wiscosoft.ridefree.domain.payment

import io.reactivex.Observable

interface PaymentApi {

  val all: () -> Observable<List<Payment>>

  val get: (Int) -> Observable<Payment>

  val add: (Payment) -> Observable<Payment>

  val modify: (Payment) -> Observable<Payment>

  val delete: (Payment) -> Observable<Payment>
}
