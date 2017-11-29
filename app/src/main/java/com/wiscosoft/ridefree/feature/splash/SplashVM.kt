package com.wiscosoft.ridefree.feature.splash

import android.Manifest
import com.apt7.rxpermissions.Permission
import com.wiscosoft.ridefree.core.extensions.threads
import com.wiscosoft.ridefree.provider.permission.Permissions
import io.reactivex.Flowable

class SplashVM
constructor(private val permissions: Permissions) {

  fun checkPermissions(): Flowable<Boolean> = permissions
    .checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA)
    .map { it.granted == Permission.PERMISSION_GRANTED }
    .reduce(Boolean::and).toFlowable()
    .compose(threads())


  fun requestPermissions(): Flowable<Boolean> = permissions
    .requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA)
    .map { it.granted == Permission.PERMISSION_GRANTED }
    .reduce(Boolean::and).toFlowable()
    .compose(threads())


}