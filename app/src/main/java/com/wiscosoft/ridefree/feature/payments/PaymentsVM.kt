package com.wiscosoft.ridefree.feature.payments

import com.braintreepayments.cardform.view.CardForm
import com.wiscosoft.ridefree.core.base.EmptyListVM
import com.wiscosoft.ridefree.core.extensions.threads
import com.wiscosoft.ridefree.domain.payment.Payment
import com.wiscosoft.ridefree.domain.payment.PaymentApi
import io.reactivex.Flowable

class PaymentsVM
(private val paymentApi: PaymentApi, val empty: EmptyListVM) {

  fun getPayment(id: Int): Flowable<Payment> = paymentApi.get(id).compose(threads())

  fun getAllPayments(): Flowable<List<Payment>> = paymentApi.all().compose(threads())

  fun addPayment(cardForm: CardForm): Flowable<Payment> = paymentApi.add(createPayment(cardForm)).compose(threads())

  fun deletePayment(id: Int): Flowable<Payment> = paymentApi.get(id).flatMap(paymentApi::delete).compose(threads())

  private fun createPayment(cardForm: CardForm): Payment {
    return Payment().apply {
      cCNumber = cardForm.cardNumber
      cVV2Number = cardForm.cvv
      cCExpiration = "${cardForm.expirationMonth}/${cardForm.expirationYear}"
      phoneNumber = "${cardForm.countryCode}-${cardForm.mobileNumber}"
    }
  }

}




