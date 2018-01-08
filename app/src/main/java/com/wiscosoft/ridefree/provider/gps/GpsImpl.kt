package com.wiscosoft.ridefree.provider.gps

import android.annotation.SuppressLint
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.LocationRequest
import com.patloew.rxlocation.RxLocation
import com.wiscosoft.ridefree.core.flowable
import io.reactivex.Flowable

@SuppressLint("MissingPermission")
class GpsImpl(private val rxLoc: RxLocation, request: LocationRequest, google: GoogleApiAvailability) : Gps {

  override val googleApi: Flowable<GoogleApiAvailability> =
    Flowable
      .just(google)

  override val settings: Flowable<Boolean> =
    rxLoc.settings()
      .checkAndHandleResolution(request)
      .flowable

  override val location: Flowable<Pos> =
    rxLoc.location()
      .updates(request)
      .map(Pos.Companion::fromLocation)
      .flowable

  override fun location(query: String): Flowable<Pos> =
    rxLoc.geocoding()
      .fromLocationName(query)
      .map(Pos.Companion::fromAddress)
      .flowable

}
