package com.wiscosoft.ridefree.feature.fragment.payments

import com.github.salomonbrys.kodein.Kodein
import com.wiscosoft.ridefree.feature.fragment.payments.add.PaymentsAddModule
import com.wiscosoft.ridefree.feature.fragment.payments.list.PaymentsListModule

class PaymentsModule {
  val bind = Kodein.Module {
    import(PaymentsAddModule().bind)
    import(PaymentsListModule().bind)
  }
}