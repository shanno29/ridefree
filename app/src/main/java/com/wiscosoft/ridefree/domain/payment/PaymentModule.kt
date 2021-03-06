package com.wiscosoft.ridefree.domain.payment

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.wiscosoft.ridefree.domain.payment.api.PaymentApi
import com.wiscosoft.ridefree.domain.payment.api.PaymentApiImp
import com.wiscosoft.ridefree.provider.storage.StorageContainer
import retrofit2.Retrofit

class PaymentModule {

  val bind = Kodein.Module {
    bind<PaymentNetwork>() with singleton { paymentNetwork(instance()) }
    bind<PaymentStorage>() with singleton { paymentStorage(instance()) }
    bind<PaymentApi>() with singleton { paymentApi(instance()) }
  }

  private fun paymentNetwork(retrofit: Retrofit): PaymentNetwork = retrofit.create(PaymentNetwork::class.java)

  private fun paymentStorage(storageContainer: StorageContainer): PaymentStorage = storageContainer.paymentStorage()

  private fun paymentApi(storage: PaymentStorage): PaymentApi = PaymentApiImp(storage)

}
