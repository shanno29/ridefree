package com.wiscosoft.ridefree.provider.gps

import android.content.Context
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationRequest.PRIORITY_LOW_POWER
import com.patloew.rxlocation.RxLocation

val gpsModule = Kodein.Module {

  bind<RxLocation>() with singleton {
    val context: Context = instance()
    RxLocation(context)
  }

  bind<LocationRequest>() with singleton {
    val priority = PRIORITY_LOW_POWER
    val interval: Long = 10 * 10000
    LocationRequest.create()
      .setPriority(priority)
      .setInterval(interval)
  }

  bind<GoogleApiAvailability>() with singleton {
    GoogleApiAvailability.getInstance()
  }

  bind<Gps>() with singleton {
    val google: GoogleApiAvailability = instance()
    val request: LocationRequest = instance()
    val rxLocation: RxLocation = instance()
    GpsImp(rxLocation, request, google)
  }

}