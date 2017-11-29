package com.wiscosoft.ridefree.domain.user

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.wiscosoft.ridefree.provider.storage.StorageContainer
import retrofit2.Retrofit

class UserModule {

  val bind = Kodein.Module {
    bind<UserNetwork>() with singleton { userNetwork(instance()) }
    bind<UserStorage>() with singleton { userStorage(instance()) }
    bind<UserApi>() with singleton { userApi(instance(), instance()) }
  }

  private fun userNetwork(retrofit: Retrofit): UserNetwork = retrofit.create(UserNetwork::class.java)

  private fun userStorage(storageContainer: StorageContainer): UserStorage = storageContainer.userStorage()

  private fun userApi(network: UserNetwork, storage: UserStorage): UserApi = UserApiImp(network, storage)

}
