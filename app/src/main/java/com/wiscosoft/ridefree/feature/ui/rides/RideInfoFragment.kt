package com.wiscosoft.ridefree.feature.ui.rides

import android.app.AlertDialog
import com.jakewharton.rxbinding2.view.RxView
import com.wiscosoft.ridefree.R
import com.wiscosoft.ridefree.core.app.showError
import com.wiscosoft.ridefree.core.app.withModels
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Layout
import com.wiscosoft.ridefree.core.base.Title
import com.wiscosoft.ridefree.core.base.goBack
import com.wiscosoft.ridefree.databinding.FragmentListBinding
import com.wiscosoft.ridefree.domain.ride.Ride
import com.wiscosoft.ridefree.titleTextCard
import javax.inject.Inject

@Title("Info")
@Layout(R.layout.fragment_list)
class RideInfoFragment : BaseFragment<FragmentListBinding>() {

  @Inject lateinit var vm: RidesVM
  internal var id: Int = 0

  override fun onReady() {
    deleteButton()
    showRide()
  }

  private fun showRide() {
    sub.add(vm.getRide(id)
      .subscribe(this::contentState, this::showError)
    )
  }

  private fun contentState(ride: Ride) {
    binding.recyclerView.withModels {
      titleTextCard {
        title(ride.restaurantId)
        text(ride.toString())
        id(ride.id)
      }
    }
  }

  private fun deleteButton() {
    binding.fab.setImageResource(R.drawable.delete_drawable)
    sub.add(RxView.clicks(binding.fab)
      .subscribe { dialogState() }
    )
  }

  private fun dialogState() {
    AlertDialog.Builder(context)
      .setNegativeButton("No") { dialog, _ -> dialog.cancel() }
      .setPositiveButton("Yes") { _, _ -> deleteRide() }
      .setMessage("Are You Sure?")
      .setTitle("Delete Ride")
      .create()
      .show()
  }

  private fun deleteRide() {
    sub.add(vm.deleteRide(id)
      .doOnNext { binding.recyclerView.removeViewAt(0) }
      .ignoreElements()
      .subscribe(this::goBack, this::showError)
    )
  }

}