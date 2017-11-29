package com.wiscosoft.ridefree.feature.rides

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.wiscosoft.ridefree.core.base.EmptyListVM
import com.wiscosoft.ridefree.domain.ride.Ride
import com.wiscosoft.ridefree.domain.ride.RideApi

class RidesModule {
  val bind = Kodein.Module {
    bind<Ride>() with provider { ride() }
    bind<EmptyListVM>("rides") with provider { emptyVM() }
    bind<RidesVM>() with provider { rideVM(instance(), instance("rides"), instance()) }
  }

  private fun ride(): Ride {
    return Ride().apply {
      pickup = "43.0048169,-88.0210398;2157 South 85th Street, West Allis, WI"
      restaurantId = "b8dfd019-f88c-473d-823b-f105457262e3"
    }
  }

  private fun emptyVM(): EmptyListVM {
    return EmptyListVM().apply {
      title = "We Have No Rides on Record For You"
      text = "Tap the plus button to add a ride!"
    }
  }

  private fun rideVM(rideApi: RideApi, empty: EmptyListVM, ride: Ride): RidesVM = RidesVM(rideApi, empty, ride)

}
