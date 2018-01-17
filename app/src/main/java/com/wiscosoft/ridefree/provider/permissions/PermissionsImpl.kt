package com.wiscosoft.ridefree.provider.permissions

import android.content.Context
import com.apt7.rxpermissions.Permission
import com.apt7.rxpermissions.PermissionObservable
import io.reactivex.Observable

class PermissionsImpl(helper: PermissionObservable, context: Context) : Permissions {

  override val check: (Array<String>) -> Observable<Permission> = {
    helper.checkThePermissionStatus(context, *it)
  }

  override val request: (Array<String>) -> Observable<Permission> = {
    helper.request(context, *it)
  }

}