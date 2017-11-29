package com.wiscosoft.ridefree.feature.rides

import com.github.salomonbrys.kodein.instance
import com.jakewharton.rxbinding2.view.RxView.clicks
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.wiscosoft.ridefree.R.layout.fragment_ride_add
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.extensions.showError
import com.wiscosoft.ridefree.core.extensions.showMessage
import com.wiscosoft.ridefree.databinding.FragmentRideAddBinding
import com.wiscosoft.ridefree.domain.ride.Ride
import io.reactivex.BackpressureStrategy.LATEST

@Config(title = "Add", layout = fragment_ride_add)
class RideAddFragment : BaseFragment<FragmentRideAddBinding>() {

  private val vm: RidesVM by injector.instance()

  override fun onReady() {
    setupAddButton()
    binding.vm = vm
  }

  private fun setupAddButton() {
    clicks(binding.rideSubmit).bindToLifecycle(provider)
      .toFlowable(LATEST)
      .flatMap { vm.addRide() }
      .subscribe({ it -> this.temp(it) }, this::showError)
  }

  private fun temp(ride: Ride) {
    showMessage(ride.toString())
  }

}