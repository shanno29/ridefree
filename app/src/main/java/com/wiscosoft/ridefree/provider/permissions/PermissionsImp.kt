package com.wiscosoft.ridefree.provider.permissions

import android.content.Context
import com.apt7.rxpermissions.Permission
import com.apt7.rxpermissions.PermissionObservable
import com.wiscosoft.ridefree.core.flowable
import io.reactivex.Flowable

// IMPLEMENTATION
class PermissionsImp(private val permObs: PermissionObservable, private val context: Context) : Permissions {

  override fun check(vararg items: String): Flowable<Permission> =
    permObs.checkThePermissionStatus(context, *items).flowable

  override fun request(vararg items: String): Flowable<Permission> =
    permObs.request(context, *items).flowable

}