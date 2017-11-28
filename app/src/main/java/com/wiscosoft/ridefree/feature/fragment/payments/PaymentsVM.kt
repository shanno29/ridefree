package com.wiscosoft.ridefree.feature.fragment.payments

import com.braintreepayments.cardform.view.CardForm
import com.wiscosoft.ridefree.core.base.EmptyListVM
import com.wiscosoft.ridefree.core.extensions.threads
import com.wiscosoft.ridefree.domain.payment.Payment
import com.wiscosoft.ridefree.provider.repo.Repo
import io.reactivex.Flowable

class PaymentsVM
(private val repo: Repo, val empty: EmptyListVM) {

  fun createPayment(cardForm: CardForm): Payment {
    return Payment().apply {
      cCNumber = cardForm.cardNumber
      cVV2Number = cardForm.cvv
      cCExpiration = "${cardForm.expirationMonth}/${cardForm.expirationYear}"
      phoneNumber = "${cardForm.countryCode}-${cardForm.mobileNumber}"
    }
  }

  fun getPayment(id: Int): Flowable<Payment> = repo.paymentApi().get(id).compose(threads())

  fun getAllPayments(): Flowable<List<Payment>> = repo.paymentApi().all().compose(threads())

  fun savePayment(payment: Payment): Flowable<Payment> = repo.paymentApi().add(payment).compose(threads())

  fun deletePayment(id: Int): Flowable<Payment> = repo.paymentApi().get(id).flatMap { repo.paymentApi().delete(it) }.compose(threads())

}




