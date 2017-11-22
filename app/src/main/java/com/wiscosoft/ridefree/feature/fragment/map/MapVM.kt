package com.wiscosoft.ridefree.feature.fragment.map

import com.wiscosoft.ridefree.core.extensions.threads
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.provider.gps.Gps
import com.wiscosoft.ridefree.provider.gps.Loc
import com.wiscosoft.ridefree.provider.prefs.Prefs
import com.wiscosoft.ridefree.provider.repo.Repo
import io.reactivex.Flowable

class MapVM
constructor(private val repo: Repo, val gps: Gps, private val prefs: Prefs) {

  fun owner(): Flowable<User> = repo.userApi().get(prefs.owner().get()).compose(threads())

  fun getLocation(name: String): Flowable<Loc> = gps.getLocation(name).compose(threads())

}