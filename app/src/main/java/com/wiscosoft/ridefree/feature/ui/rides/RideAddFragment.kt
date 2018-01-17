package com.wiscosoft.ridefree.feature.ui.rides

import com.wiscosoft.ridefree.R
import com.wiscosoft.ridefree.core.app.showMessage
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Layout
import com.wiscosoft.ridefree.core.base.Title
import com.wiscosoft.ridefree.databinding.FragmentRideAddBinding
import com.wiscosoft.ridefree.domain.ride.Ride
import javax.inject.Inject

@Title("Add")
@Layout(R.layout.fragment_ride_add)
class RideAddFragment : BaseFragment<FragmentRideAddBinding>() {

  @Inject lateinit var vm: RidesVM

  override fun onReady() {
    addButton()
  }

  private fun addButton() {
//    sub.add(clicks(binding.rideSubmit)
//      .flatMap { vm.addRide(ride, Payment.DEFAULT) }
//      .subscribe(this::showSuccess, this::showError)
//    )
  }

  private fun showSuccess(ride: Ride) {
    showMessage("Ride $ride.id Submitted!")
  }

}