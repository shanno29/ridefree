package com.wiscosoft.ridefree.provider.permission


import com.apt7.rxpermissions.Permission
import io.reactivex.Flowable

interface Permissions {

  fun checkPermission(vararg permissions: String): Flowable<Permission>

  fun requestPermission(vararg permissions: String): Flowable<Permission>

}