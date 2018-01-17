package com.wiscosoft.ridefree.feature.ui.map

import android.content.Context
import com.google.android.gms.maps.model.MapStyleOptions
import com.wiscosoft.ridefree.R
import com.wiscosoft.ridefree.provider.gps.Gps
import com.wiscosoft.ridefree.provider.redux.State
import dagger.Module
import dagger.Provides
import redux.api.Store

@Module
class MapModule {

  @Provides
  fun mayStyle(context: Context): MapStyleOptions {
    return MapStyleOptions.loadRawResourceStyle(context, R.raw.map_full)
  }

  @Provides
  fun mapVM(gps: Gps, store: Store<State>, mapStyleOptions: MapStyleOptions): MapVM {
    return MapVM(gps, store, mapStyleOptions)
  }

}