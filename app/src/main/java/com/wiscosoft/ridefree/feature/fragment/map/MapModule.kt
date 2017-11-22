package com.wiscosoft.ridefree.feature.fragment.map

import android.content.Context
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MapStyleOptions.loadRawResourceStyle
import com.wiscosoft.ridefree.R.raw.map_full
import com.wiscosoft.ridefree.provider.gps.Gps
import com.wiscosoft.ridefree.provider.prefs.Prefs
import com.wiscosoft.ridefree.provider.repo.Repo

class MapModule {

  val bind = Kodein.Module {
    bind() from singleton { mapStyle(instance()) }
    bind() from singleton { vm(instance(), instance(), instance()) }
  }

  private fun mapStyle(context: Context): MapStyleOptions = loadRawResourceStyle(context, map_full)

  private fun vm(repo: Repo, gps: Gps, prefs: Prefs): MapVM = MapVM(repo, gps, prefs)

}