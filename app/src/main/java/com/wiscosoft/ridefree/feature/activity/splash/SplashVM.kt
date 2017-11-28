package com.wiscosoft.ridefree.feature.activity.splash

import android.Manifest.permission.*
import com.apt7.rxpermissions.Permission
import com.wiscosoft.ridefree.core.extensions.threads
import com.wiscosoft.ridefree.provider.permission.Permissions
import io.reactivex.Flowable

class SplashVM
constructor(private val permissions: Permissions) {

  fun checkPermissions(): Flowable<Boolean> = permissions
    .checkPermission(WRITE_EXTERNAL_STORAGE, ACCESS_FINE_LOCATION, CAMERA)
    .map { it.granted == Permission.PERMISSION_GRANTED }
    .compose(threads())


  fun requestPermissions(): Flowable<Boolean> = permissions
    .requestPermission(WRITE_EXTERNAL_STORAGE, ACCESS_FINE_LOCATION, CAMERA)
    .map { it.granted == Permission.PERMISSION_GRANTED }
    .compose(threads())

}
