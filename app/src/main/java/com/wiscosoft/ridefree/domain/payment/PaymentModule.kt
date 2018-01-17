package com.wiscosoft.ridefree.domain.payment

import com.wiscosoft.ridefree.provider.storage.StorageContainer
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PaymentModule {

  @Provides
  @Singleton
  fun paymentStorage(container: StorageContainer): PaymentStorage {
    return container.paymentStorage
  }

  @Provides
  @Singleton
  fun paymentApi(storage: PaymentStorage): PaymentApi {
    return PaymentApiImp(storage)
  }

}