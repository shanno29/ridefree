package com.wiscosoft.ridefree.feature.ui.payments

import com.wiscosoft.ridefree.domain.payment.PaymentApi
import com.wiscosoft.ridefree.provider.redux.State
import dagger.Module
import dagger.Provides
import redux.api.Store

@Module
class PaymentsModule {

  @Provides
  fun paymentsVM(paymentApi: PaymentApi, store: Store<State>): PaymentsVM {
    return PaymentsVM(paymentApi, store)
  }

}