package com.wiscosoft.ridefree.feature.ui.payments

import com.braintreepayments.cardform.view.CardForm
import com.wiscosoft.ridefree.domain.payment.Payment
import com.wiscosoft.ridefree.domain.payment.PaymentApi
import com.wiscosoft.ridefree.provider.redux.State
import com.wiscosoft.ridefree.provider.rx.setThreads
import io.reactivex.Observable
import redux.api.Store

class PaymentsVM(paymentApi: PaymentApi, store: Store<State>) {

  val getAllPayments: () -> Observable<List<Payment>> = { paymentApi.all().setThreads() }

  val getPayment: (Int) -> Observable<Payment> = { paymentApi.get(it).setThreads() }

  val deletePayment: (Int) -> Observable<Payment> = { getPayment(it).flatMap { paymentApi.delete(it) }.setThreads() }

  val addPayment: (CardForm) -> Observable<Payment> = { paymentApi.add(createPayment(it)).setThreads() }

  val createPayment: (CardForm) -> Payment = {
    Payment.DEFAULT.copy(
      cCNumber = it.cardNumber,
      cVV2Number = it.cvv,
      cCExpiration = "${it.expirationMonth}/${it.expirationYear}",
      phoneNumber = "${it.countryCode}-${it.mobileNumber}"
    )
  }

}
