package com.wiscosoft.ridefree.domain.user.api

import com.wiscosoft.ridefree.core.extensions.log
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.domain.user.UserNetwork
import com.wiscosoft.ridefree.domain.user.UserStorage
import com.wiscosoft.ridefree.provider.network.RequestWrapper
import io.reactivex.Flowable

class UserApiImp
constructor(private val network: UserNetwork, private val storage: UserStorage) : UserApi {

  override operator fun get(id: Int): Flowable<User> {
    return storage.get(id)
        .compose(log())
  }

  override fun modify(user: User): Flowable<User> {
    return network.modify(user)
        .doOnNext { storage.modify(it) }
        .compose(log())
  }

  override fun register(user: User): Flowable<User> {
    return network.register(user)
        .compose(log())
  }

  override fun logon(user: User): Flowable<User> {
    return network.logon(user)
        .doOnNext { storage.add(user) }
        .compose(log())
  }

  override fun logoff(user: User): Flowable<User> {
    return network.logoff(user)
        .doOnComplete { storage.delete(user) }
        .compose(log())
  }

}
