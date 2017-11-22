package com.wiscosoft.ridefree.provider.gps

import android.location.Address
import android.location.Location
import com.google.android.gms.maps.model.LatLng

class Loc(private var lat: Double, private var lon: Double) {

  constructor(loc: Location) : this(loc.latitude, loc.longitude)

  constructor(address: Address) : this(address.latitude, address.longitude)

  fun toLatLn(): LatLng = LatLng(lat, lon)

}
