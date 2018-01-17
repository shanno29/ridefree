package com.wiscosoft.ridefree.domain.user

import io.reactivex.Observable

interface UserApi {

  val get: (Int) -> Observable<User>

  val modify: (User) -> Observable<User>

  val register: (User) -> Observable<User>

  val logon: (User) -> Observable<User>

  val logoff: (User) -> Observable<User>
}