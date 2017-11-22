package com.wiscosoft.ridefree.provider.gps

import io.reactivex.Flowable

interface Gps {

  fun getLocation(): Flowable<Loc>

  //fun checkPermissions(): Flowable<Boolean>

  fun checkGps(): Flowable<Boolean>

  //fun checkConditions(): Flowable<Boolean>

  fun getLocation(query: String): Flowable<Loc>
}