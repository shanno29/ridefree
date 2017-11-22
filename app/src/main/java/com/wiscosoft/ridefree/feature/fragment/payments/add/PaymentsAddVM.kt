package com.wiscosoft.ridefree.feature.fragment.payments.add

import com.braintreepayments.cardform.view.CardForm
import com.wiscosoft.ridefree.core.extensions.randomId
import com.wiscosoft.ridefree.core.extensions.threads
import com.wiscosoft.ridefree.domain.payment.Payment
import com.wiscosoft.ridefree.provider.repo.Repo
import io.reactivex.Flowable

class PaymentsAddVM
constructor(private val repo: Repo) {

  fun save(cardForm: CardForm): Flowable<Payment> {
    return repo.paymentApi()
        .add(createPayment(cardForm))
        .compose(threads())
  }

  private fun createPayment(cardForm: CardForm): Payment {
    return Payment(
        randomId(),
        cardForm.cardNumber,
        cardForm.cvv,
        (cardForm.expirationMonth + cardForm.expirationYear)
    )
  }

}




