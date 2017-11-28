package com.wiscosoft.ridefree.domain.payment

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.wiscosoft.ridefree.domain.payment.api.PaymentApi
import com.wiscosoft.ridefree.domain.payment.api.PaymentApiImp
import com.wiscosoft.ridefree.provider.storage.StorageContainer

class PaymentModule {

  val bind = Kodein.Module {
    bind<PaymentStorage>() with singleton { paymentStorage(instance()) }
    bind<PaymentApi>() with singleton { paymentApi(instance()) }
  }

  private fun paymentStorage(storageContainer: StorageContainer): PaymentStorage = storageContainer.paymentStorage()

  private fun paymentApi(storage: PaymentStorage): PaymentApi = PaymentApiImp(storage)

}
