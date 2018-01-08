package com.wiscosoft.ridefree.provider.gps

import android.location.Address
import android.location.Location

data class Pos(val id: Int, val lat: Double, val lon: Double) {
  companion object {

    val DEFAULT = Pos(0, 0.0, 0.0)

    fun from(x: Double, y: Double): Pos = DEFAULT.copy(lat = x, lon = y)

    fun fromLocation(loc: Location): Pos = from(loc.latitude, loc.longitude)
    fun fromAddress(adr: Address): Pos = from(adr.latitude, adr.longitude)
    val MIDDLE_OF_USA: Pos = from(40.0, 100.0)

  }
}