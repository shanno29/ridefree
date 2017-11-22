package com.wiscosoft.ridefree.provider.router

import android.support.v4.app.Fragment

interface Router {

  fun fromTitle(name: String): Fragment

  fun register(): Fragment

  fun login(): Fragment

  fun logout(): Fragment

  fun about(): Fragment

  fun history(): Fragment

  fun historyDetail(id: Int): Fragment

  fun map(): Fragment

  fun paymentAdd(): Fragment

  fun payment(): Fragment

  fun promos(): Fragment

  fun settings(): Fragment

}

