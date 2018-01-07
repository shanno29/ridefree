package com.wiscosoft.ridefree.domain.user

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.wiscosoft.ridefree.domain.user.api.UserApi
import com.wiscosoft.ridefree.domain.user.api.UserApiImp
import com.wiscosoft.ridefree.domain.user.api.UserNetwork
import com.wiscosoft.ridefree.domain.user.api.UserStorage
import com.wiscosoft.ridefree.provider.storage.StorageContainer
import retrofit2.Retrofit

val userModule = Kodein.Module {

  bind<UserNetwork>() with singleton {
    val retrofit: Retrofit = instance()
    retrofit.create(UserNetwork::class.java)
  }

  bind<UserStorage>() with singleton {
    val container: StorageContainer = instance()
    container.userStorage()
  }

  bind<UserApi>() with singleton {
    val network: UserNetwork = instance()
    val storage: UserStorage = instance()
    UserApiImp(network, storage)
  }

}
