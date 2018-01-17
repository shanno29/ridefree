package com.wiscosoft.ridefree.domain.user

import io.reactivex.Observable

class UserApiImp(network: UserNetwork, storage: UserStorage) : UserApi {

  override val get: (Int) -> Observable<User> = { id ->
    Observable.just(id)
      .flatMap { storage.get(id).toObservable() }
  }

  override val modify: (User) -> Observable<User> = { user ->
    Observable.just(user)
      .flatMap { network.modify(user) }
      .doOnNext { storage.modify(user) }
  }

  override val register: (User) -> Observable<User> = { user ->
    Observable.just(user)
      .flatMap { network.register(user) }
  }

  override val logon: (User) -> Observable<User> = { user ->
    Observable.just(user)
      .flatMap { network.logon(user) }
      .doOnNext { storage.add(user) }
  }

  override val logoff: (User) -> Observable<User> = { user ->
    Observable.just(user)
      .flatMap { network.logoff(user) }
      .doOnNext { storage.delete(user) }
  }
}