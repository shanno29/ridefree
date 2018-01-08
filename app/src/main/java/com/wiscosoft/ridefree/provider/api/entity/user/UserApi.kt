package com.wiscosoft.ridefree.provider.api.entity.user

import com.wiscosoft.ridefree.domain.user.User
import io.reactivex.Flowable

interface UserApi {

  fun get(id: Int): Flowable<User>

  fun modify(user: User): Flowable<User>

  fun register(user: User): Flowable<User>

  fun logon(user: User): Flowable<User>

  fun logoff(user: User): Flowable<User>
}