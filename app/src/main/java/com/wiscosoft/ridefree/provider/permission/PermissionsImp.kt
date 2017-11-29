package com.wiscosoft.ridefree.provider.permission

import android.content.Context
import com.apt7.rxpermissions.Permission
import com.apt7.rxpermissions.PermissionObservable
import com.wiscosoft.ridefree.core.extensions.log
import io.reactivex.BackpressureStrategy.LATEST
import io.reactivex.Flowable

class PermissionsImp
constructor(private val permissionHelper: PermissionObservable, private val context: Context) : Permissions {

//  @RxLogFlowable
  override fun checkPermission(vararg permissions: String): Flowable<Permission> {
    return permissionHelper.checkThePermissionStatus(context, *permissions)
      .toFlowable(LATEST)
      .compose(log())

  }

  override fun requestPermission(vararg permissions: String): Flowable<Permission> {
    return permissionHelper
      .request(context, *permissions)
      .toFlowable(LATEST)
      .compose(log())
  }


}