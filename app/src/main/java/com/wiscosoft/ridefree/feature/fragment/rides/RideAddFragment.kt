package com.wiscosoft.ridefree.feature.fragment.rides

import com.github.salomonbrys.kodein.instance
import com.jakewharton.rxbinding2.view.RxView
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.wiscosoft.ridefree.R.layout.fragment_ride_add
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.extensions.goBack
import com.wiscosoft.ridefree.core.extensions.showError
import com.wiscosoft.ridefree.databinding.FragmentRideAddBinding

@Config(title = "Add", layout = fragment_ride_add)
class RideAddFragment : BaseFragment<FragmentRideAddBinding>() {

  private val vm: RidesVM by injector.instance()

  override fun onReady() {
    setupAddButton()
    binding.vm = vm
  }

  private fun setupAddButton() {
    RxView.clicks(binding.rideSubmit)
      .bindToLifecycle(provider)
      .map { vm.addRide() }
      .subscribe({ goBack() }, this::showError)
  }

}