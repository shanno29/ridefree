package com.wiscosoft.ridefree.domain.user

import com.wiscosoft.ridefree.provider.storage.StorageContainer
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class UserModule {

  @Provides
  @Singleton
  fun userNetwork(retrofit: Retrofit): UserNetwork {
    return retrofit.create(UserNetwork::class.java)
  }

  @Provides
  @Singleton
  fun userStorage(container: StorageContainer): UserStorage {
    return container.userStorage
  }

  @Provides
  @Singleton
  fun userApi(userNetwork: UserNetwork, userStorage: UserStorage): UserApi {
    return UserApiImp(userNetwork, userStorage)
  }

}