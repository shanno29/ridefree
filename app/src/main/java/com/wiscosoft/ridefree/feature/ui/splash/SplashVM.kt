package com.wiscosoft.ridefree.feature.ui.splash

import com.apt7.rxpermissions.Permission.PERMISSION_GRANTED
import com.google.android.gms.common.GoogleApiAvailability
import com.wiscosoft.ridefree.provider.gps.Gps
import com.wiscosoft.ridefree.provider.permissions.Permissions
import com.wiscosoft.ridefree.provider.rx.setThreads
import io.reactivex.Observable
import io.reactivex.Single

class SplashVM(permissions: Permissions, gps: Gps, items: Array<String>) {

  val checkPermissions: () -> Single<Boolean> = {
    permissions
      .check(items)
      .map { it.granted == PERMISSION_GRANTED }
      .reduce(Boolean::and)
      .toSingle()
      .setThreads()
  }

  val requestPermissions: () -> Single<Boolean> = {
    permissions
      .request(items)
      .map { it.granted == PERMISSION_GRANTED }
      .reduce(Boolean::and)
      .toSingle()
      .setThreads()
  }

  val checkGoogleApi: () -> Observable<GoogleApiAvailability> = {
    gps
      .googleApi
      .setThreads()
  }

}