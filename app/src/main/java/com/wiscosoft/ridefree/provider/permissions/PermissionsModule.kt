package com.wiscosoft.ridefree.provider.permissions

import android.content.Context
import com.apt7.rxpermissions.PermissionObservable
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PermissionsModule {

  @Provides
  @Singleton
  fun permissionHelper(): PermissionObservable {
    return PermissionObservable.getInstance()
  }

  @Provides
  @Singleton
  fun permissions(permissionsHelper: PermissionObservable, context: Context): Permissions {
    return PermissionsImpl(permissionsHelper, context)
  }

}
