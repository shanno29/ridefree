package com.wiscosoft.ridefree.provider.gps

import com.google.android.gms.common.GoogleApiAvailability
import io.reactivex.Flowable

interface Gps {

  val googleApi: Flowable<GoogleApiAvailability>

  val settings: Flowable<Boolean>

  val location: Flowable<Pos>

  fun location(query: String): Flowable<Pos>
}
