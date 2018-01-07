package com.wiscosoft.ridefree.domain.payment

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.wiscosoft.ridefree.domain.payment.api.PaymentApi
import com.wiscosoft.ridefree.domain.payment.api.PaymentApiImp
import com.wiscosoft.ridefree.domain.payment.api.PaymentStorage
import com.wiscosoft.ridefree.provider.storage.StorageContainer

val paymentModule = Kodein.Module {

  bind<PaymentStorage>() with singleton {
    val container: StorageContainer = instance()
    container.paymentStorage()
  }

  bind<PaymentApi>() with singleton {
    val storage: PaymentStorage = instance()
    PaymentApiImp(storage)
  }
}