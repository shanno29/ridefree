package com.wiscosoft.ridefree.provider.api.entity.user

import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.domain.user.UserNetwork
import com.wiscosoft.ridefree.domain.user.UserStorage
import io.reactivex.Flowable

// IMPLEMENTATION
class UserApiImp(private val network: UserNetwork, private val storage: UserStorage) : UserApi {

  override fun get(id: Int): Flowable<User> = storage.get(id)

  override fun modify(user: User): Flowable<User> = network.modify(user).doOnNext { storage.modify(it) }

  override fun register(user: User): Flowable<User> = network.register(user)

  override fun logon(user: User): Flowable<User> = network.logon(user).doOnNext { storage.add(user) }

  override fun logoff(user: User): Flowable<User> = network.logoff(user).doOnNext { storage.delete(user) }
}