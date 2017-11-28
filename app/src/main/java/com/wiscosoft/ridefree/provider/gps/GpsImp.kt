package com.wiscosoft.ridefree.provider.gps

import android.annotation.SuppressLint
import com.google.android.gms.location.LocationRequest
import com.patloew.rxlocation.RxLocation
import com.wiscosoft.ridefree.core.extensions.log
import io.reactivex.BackpressureStrategy.BUFFER
import io.reactivex.Flowable

class GpsImp
constructor(private val request: LocationRequest, private val rxLocation: RxLocation) : Gps {

  override fun checkGps(): Flowable<Boolean> {
    return rxLocation.settings()
      .checkAndHandleResolution(request)
      .toFlowable()
      .compose(log())
  }

  @SuppressLint("MissingPermission")
  override fun getLocation(): Flowable<Loc> {
    return rxLocation.location()
      .updates(request)
      .map { Loc(it) }
      .toFlowable(BUFFER)
      .compose(log())
  }

  override fun getLocation(query: String): Flowable<Loc> {
    return rxLocation.geocoding()
      .fromLocationName(query)
      .map { Loc(it) }
      .toFlowable()
      .compose(log())
  }

}
