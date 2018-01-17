package com.wiscosoft.ridefree.feature.ui.map

import com.google.android.gms.maps.model.MapStyleOptions
import com.wiscosoft.ridefree.provider.gps.Gps
import com.wiscosoft.ridefree.provider.gps.Loc
import com.wiscosoft.ridefree.provider.redux.State
import com.wiscosoft.ridefree.provider.rx.setThreads
import io.reactivex.Observable
import redux.api.Store
import redux.asObservable

class MapVM(gps: Gps, store: Store<State>, style: MapStyleOptions) {

  val awaitGps: Observable<MapStyleOptions> =
    gps.settings
      .filter { it }
      .map { style }
      .setThreads()

  val locUpdates: Observable<Loc> =
    store.asObservable()
      .map(State::loc)
      .setThreads()

}