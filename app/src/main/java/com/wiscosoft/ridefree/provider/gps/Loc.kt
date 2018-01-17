package com.wiscosoft.ridefree.provider.gps

import android.location.Address
import android.location.Location
import com.google.android.gms.maps.model.LatLng

data class Loc(val id: Int, val lat: Double, val lon: Double) {

  val toLatLng: LatLng = LatLng(lat, lon)

  companion object {

    val DEFAULT = Loc(0, 0.0, 0.0)

    fun from(x: Double, y: Double): Loc = DEFAULT.copy(lat = x, lon = y)

    fun fromLocation(loc: Location): Loc = from(loc.latitude, loc.longitude)
    fun fromAddress(adr: Address): Loc = from(adr.latitude, adr.longitude)
    val MIDDLE_OF_USA: Loc = from(40.0, 100.0)

  }
}