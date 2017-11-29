package com.wiscosoft.ridefree.feature.map

import com.wiscosoft.ridefree.core.extensions.threads
import com.wiscosoft.ridefree.provider.gps.Gps
import com.wiscosoft.ridefree.provider.gps.Loc
import io.reactivex.Flowable

class MapVM
constructor(val gps: Gps) {

  fun getLocation(name: String): Flowable<Loc> = gps.getLocation(name).compose(threads())

}