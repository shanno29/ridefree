package com.wiscosoft.ridefree.provider.permission

import android.content.Context
import com.apt7.rxpermissions.PermissionObservable
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton

class PermissionsModule {

  val bind = Kodein.Module {
    bind() from singleton { permissionObservable() }
    bind<Permissions>() with singleton { permissions(instance(), instance()) }
  }

  private fun permissionObservable(): PermissionObservable {
    return PermissionObservable.getInstance()
  }

  private fun permissions(permissionObservable: PermissionObservable, context: Context): Permissions {
    return PermissionsImp(permissionObservable, context)
  }

}


