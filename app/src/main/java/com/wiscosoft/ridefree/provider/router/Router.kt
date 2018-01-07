package com.wiscosoft.ridefree.provider.router

import android.support.v4.app.Fragment

interface Router {

  fun fromTitle(name: CharSequence): Fragment

  val splash: Fragment

  val register: Fragment

  val login: Fragment

  val logout: Fragment

  val about: Fragment

//  fun rideAdd(): Fragment
//  fun rideList(): Fragment
//  fun rideInfo(id: Int): Fragment

  val home: Fragment

//  fun paymentAdd(): Fragment
//  fun paymentList(): Fragment
//  fun paymentInfo(id: Int): Fragment

  val promos: Fragment

  val settings: Fragment
}



