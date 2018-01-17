package com.wiscosoft.ridefree.provider

import com.wiscosoft.ridefree.provider.gps.GpsModule
import com.wiscosoft.ridefree.provider.network.NetworkModule
import com.wiscosoft.ridefree.provider.permissions.PermissionsModule
import com.wiscosoft.ridefree.provider.push.PushModule
import com.wiscosoft.ridefree.provider.redux.ReduxModule
import com.wiscosoft.ridefree.provider.storage.StorageModule
import dagger.Module

@Module(includes = [
  PermissionsModule::class,
  NetworkModule::class,
  StorageModule::class,
  ReduxModule::class,
  PushModule::class,
  GpsModule::class]
)
class ProviderModule