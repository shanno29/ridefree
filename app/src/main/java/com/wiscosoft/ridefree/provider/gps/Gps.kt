package com.wiscosoft.ridefree.provider.gps

import com.google.android.gms.common.GoogleApiAvailability
import io.reactivex.Observable

interface Gps {

  val googleApi: Observable<GoogleApiAvailability>

  val settings: Observable<Boolean>

  val location: Observable<Loc>

  val locationFrom: (String) -> Observable<Loc>
}
