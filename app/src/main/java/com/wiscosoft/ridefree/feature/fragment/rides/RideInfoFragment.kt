package com.wiscosoft.ridefree.feature.fragment.rides

import android.app.AlertDialog
import com.github.salomonbrys.kodein.instance
import com.jakewharton.rxbinding2.view.RxView.clicks
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.wiscosoft.ridefree.R.drawable.delete_24dp
import com.wiscosoft.ridefree.R.layout.fragment_list
import com.wiscosoft.ridefree.TitleTextCardBindingModel_
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.extensions.goBack
import com.wiscosoft.ridefree.core.extensions.showError
import com.wiscosoft.ridefree.databinding.FragmentListBinding
import com.wiscosoft.ridefree.domain.ride.Ride

@Config(title = "Info", layout = fragment_list)
class RideInfoFragment : BaseFragment<FragmentListBinding>() {

  private val vm: RidesVM by injector.instance()
  private val img: Int = delete_24dp
  internal var id: Int = 0

  override fun onReady() {
    binding.fab.setImageResource(img)
    setupDeleteButton()
    showRide()
  }

  private fun setupDeleteButton() {
    clicks(binding.fab)
      .bindToLifecycle(provider)
      .subscribe { showDialog() }
  }

  private fun showRide() {
    vm.getRide(id)
      .bindToLifecycle(provider)
      .map(this::contentView)
      .subscribe(binding.recyclerView::setModels, this::showError)
  }

  private fun contentView(ride: Ride) = listOf(
    TitleTextCardBindingModel_().apply {
      title(ride.restaurantId)
      text(ride.toString())
      id(ride.id)
    }
  )

  private fun deleteRide() {
    vm.deleteRide(id)
      .bindToLifecycle(provider)
      .doOnNext { binding.recyclerView.removeViewAt(0) }
      .subscribe({ goBack() }, this::showError)
  }

  private fun showDialog() {
    AlertDialog.Builder(context)
      .setPositiveButton("Yes") { _, _ -> deleteRide() }
      .setNegativeButton("No") { dialog, _ -> dialog.cancel() }
      .setMessage("Are You Sure?")
      .setTitle("Delete Ride")
      .create()
      .show()
  }

}
