package com.wiscosoft.ridefree.feature.fragment.history.list

import com.wiscosoft.ridefree.core.extensions.threads
import com.wiscosoft.ridefree.domain.ride.Ride
import com.wiscosoft.ridefree.provider.repo.Repo
import io.reactivex.Flowable

class HistoryVM
constructor(private val repo: Repo) {

  fun rides(): Flowable<List<Ride>> = repo.rideApi().all(10, 0).compose(threads())

}
