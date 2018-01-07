package com.wiscosoft.ridefree.provider.gps

import android.location.Address
import android.location.Location

data class Position(val lat: Double, val lon: Double) {

  constructor(location: Location) : this(location.latitude, location.longitude)

  constructor(address: Address) : this(address.latitude, address.longitude)

  companion object {
    val default = Position(40.0, 100.0)
  }
}