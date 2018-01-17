package com.wiscosoft.ridefree.provider.gps

import android.content.Context
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationRequest.PRIORITY_LOW_POWER
import com.patloew.rxlocation.RxLocation
import com.wiscosoft.ridefree.core.app.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GpsModule {

  @Provides
  @Singleton
  fun rxLocation(context: Context): RxLocation {
    return RxLocation(context)
  }

  @Provides
  @Singleton
  fun locationRequest(): LocationRequest {
   return LocationRequest.create()
     .setPriority(PRIORITY_LOW_POWER)
     .setInterval(Constants.gpsInterval)
  }

  @Provides
  @Singleton
  fun googleApiStatus(): GoogleApiAvailability {
    return GoogleApiAvailability.getInstance()
  }

  @Provides
  @Singleton
  fun gps(rxLocation: RxLocation, request: LocationRequest, google: GoogleApiAvailability): Gps {
    return GpsImpl(rxLocation, request, google)
  }

}