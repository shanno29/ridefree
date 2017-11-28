package com.wiscosoft.ridefree.provider.router

import android.support.v4.app.Fragment

interface Router {

  fun fromTitle(name: String): Fragment

  fun register(): Fragment

  fun login(): Fragment

  fun logout(): Fragment

  fun about(): Fragment

  fun rideAdd(): Fragment
  fun rideList(): Fragment
  fun rideInfo(id: Int): Fragment

  fun map(): Fragment

  fun paymentAdd(): Fragment
  fun paymentList(): Fragment
  fun paymentInfo(id: Int): Fragment

  fun promos(): Fragment

  fun settings(): Fragment

}

