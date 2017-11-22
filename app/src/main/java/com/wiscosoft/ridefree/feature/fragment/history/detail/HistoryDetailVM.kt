package com.wiscosoft.ridefree.feature.fragment.history.detail

import com.wiscosoft.ridefree.core.extensions.threads
import com.wiscosoft.ridefree.domain.ride.Ride
import com.wiscosoft.ridefree.provider.repo.Repo
import io.reactivex.Flowable

class HistoryDetailVM
constructor(private val repo: Repo) {

  fun ride(id: Int): Flowable<Ride> = repo.rideApi().get(id).compose(threads())

}
