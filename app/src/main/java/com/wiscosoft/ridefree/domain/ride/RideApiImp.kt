package com.wiscosoft.ridefree.domain.ride

import io.reactivex.Observable

class RideApiImp(storage: RideStorage) : RideApi {

  override val all: (Map<String, Int>) -> Observable<List<Ride>> = { data ->
    storage.all().toObservable()
  }


  override val get: (Int) -> Observable<Ride> = { id ->
    storage.get(id).toObservable()
  }

  override val add: (Map<String, String>) -> Observable<Ride> = { data ->
    Observable.just(Ride.DEFAULT)
//    network.add(data)
//      .flatMap {
//        storage.add(it)
//        Observable.just(it)
//      }
  }

  override val modify: (Ride) -> Observable<Ride> = { ride ->
    Observable
      //.just(network.modify(ride))
      .just { storage.modify(ride) }
      .flatMap { storage.get(ride.id).toObservable() }
  }

  override val delete: (Ride) -> Observable<Ride> = { ride ->
    Observable
      //.just(network.delete(ride))
      .just { storage.delete(ride) }
      .map { _ -> ride }
  }

}
