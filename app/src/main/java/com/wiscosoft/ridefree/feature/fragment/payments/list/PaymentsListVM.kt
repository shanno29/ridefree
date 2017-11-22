package com.wiscosoft.ridefree.feature.fragment.payments.list

import com.wiscosoft.ridefree.core.extensions.threads
import com.wiscosoft.ridefree.domain.payment.Payment
import com.wiscosoft.ridefree.provider.repo.Repo
import io.reactivex.Flowable

class PaymentsListVM
constructor(private val repo: Repo) {

  fun payments(): Flowable<List<Payment>> {
    return repo.paymentApi().all().compose(threads())
  }

  fun deletePayment(payment: Payment): Flowable<Payment> {
    return repo.paymentApi().delete(payment).compose(threads())
  }

}
