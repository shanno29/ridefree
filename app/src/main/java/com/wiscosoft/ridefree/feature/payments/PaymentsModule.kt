package com.wiscosoft.ridefree.feature.payments

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.wiscosoft.ridefree.core.base.EmptyListVM
import com.wiscosoft.ridefree.domain.payment.PaymentApi

class PaymentsModule {
  val bind = Kodein.Module {
    bind<EmptyListVM>("payments") with provider { emptyVM() }
    bind<PaymentsVM>() with provider { paymentVM(instance(), instance("payments")) }
  }

  private fun emptyVM(): EmptyListVM {
    return EmptyListVM().apply {
      title = "You Have Yet to Add a Payment"
      text = "Tap the plus button to enter a payment!"
    }
  }

  private fun paymentVM(paymentApi: PaymentApi, emptyVM: EmptyListVM): PaymentsVM = PaymentsVM(paymentApi, emptyVM)

}