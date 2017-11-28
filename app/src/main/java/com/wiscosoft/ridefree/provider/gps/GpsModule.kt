package com.wiscosoft.ridefree.provider.gps

import android.content.Context
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationRequest.create
import com.patloew.rxlocation.RxLocation

class GpsModule {

  val bind = Kodein.Module {
    bind() from singleton { rxLocation(instance()) }
    bind() from singleton { locationRequest() }
    bind<Gps>() with singleton { gps(instance(), instance()) }
  }

  private fun locationRequest(): LocationRequest {
    return create()
      .setPriority(PRIORITY_HIGH_ACCURACY)
      .setInterval(5000)
  }

  private fun rxLocation(context: Context): RxLocation {
    return RxLocation(context)
  }

  private fun gps(request: LocationRequest, rxLocation: RxLocation): Gps {
    return GpsImp(request, rxLocation)
  }

}
