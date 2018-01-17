package com.wiscosoft.ridefree.provider.gps

import android.annotation.SuppressLint
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.LocationRequest
import com.patloew.rxlocation.RxLocation
import io.reactivex.Observable

class GpsImpl(rxLoc: RxLocation, request: LocationRequest, google: GoogleApiAvailability) : Gps {

  override val googleApi: Observable<GoogleApiAvailability> =
    Observable
      .just(google)

  override val settings: Observable<Boolean>  =
    rxLoc.settings()
      .checkAndHandleResolution(request)
      .toObservable()

  @SuppressLint("MissingPermission")
  override val location: Observable<Loc> =
    rxLoc.location()
      .updates(request)
      .map(Loc.Companion::fromLocation)

  override val locationFrom: (String) -> Observable<Loc> = {
    rxLoc.geocoding()
      .fromLocationName(it)
      .map(Loc.Companion::fromAddress)
      .toObservable()
  }


}
