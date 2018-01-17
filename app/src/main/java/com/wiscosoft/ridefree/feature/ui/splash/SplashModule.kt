package com.wiscosoft.ridefree.feature.ui.splash

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CAMERA
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import com.wiscosoft.ridefree.provider.gps.Gps
import com.wiscosoft.ridefree.provider.permissions.Permissions
import dagger.Module
import dagger.Provides

@Module
class SplashModule {

  @Provides
  fun items(): Array<String> {
    return arrayOf(
      WRITE_EXTERNAL_STORAGE,
      ACCESS_FINE_LOCATION,
      CAMERA
    )
  }

  @Provides
  fun splashVM(permissions: Permissions, gps: Gps, items: Array<String>): SplashVM {
    return SplashVM(permissions, gps, items)
  }

}