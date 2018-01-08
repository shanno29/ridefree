package com.wiscosoft.ridefree.provider.api.entity.payment

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.wiscosoft.ridefree.domain.payment.PaymentStorage
import com.wiscosoft.ridefree.provider.api.storage.StorageContainer

val paymentModule = Kodein.Module {

  bind<PaymentStorage>() with singleton {
    val container: StorageContainer = instance()
    container.paymentStorage
  }

  bind<PaymentApi>() with singleton {
    val storage: PaymentStorage = instance()
    PaymentApiImp(storage)
  }
}