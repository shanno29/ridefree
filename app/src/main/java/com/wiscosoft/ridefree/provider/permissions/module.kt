package com.wiscosoft.ridefree.provider.permissions

import android.content.Context
import com.apt7.rxpermissions.PermissionObservable
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton

val permissionsModule = Kodein.Module {

  bind<PermissionObservable>() with singleton {
    PermissionObservable.getInstance()
  }

  bind<Permissions>() with singleton {
    val permObs: PermissionObservable = instance()
    val context: Context = instance()
    PermissionsImp(permObs, context)
  }

}
