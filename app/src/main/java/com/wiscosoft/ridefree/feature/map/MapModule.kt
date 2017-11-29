package com.wiscosoft.ridefree.feature.map

import android.content.Context
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MapStyleOptions.loadRawResourceStyle
import com.wiscosoft.ridefree.R.raw.map_full
import com.wiscosoft.ridefree.provider.gps.Gps

class MapModule {

  val bind = Kodein.Module {
    bind<MapStyleOptions>() with singleton { mapStyle(instance()) }
    bind<MapVM>() with singleton { vm(instance()) }
  }

  private fun mapStyle(context: Context): MapStyleOptions = loadRawResourceStyle(context, map_full)

  private fun vm(gps: Gps): MapVM = MapVM(gps)

}