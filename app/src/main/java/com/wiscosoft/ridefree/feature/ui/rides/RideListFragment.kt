package com.wiscosoft.ridefree.feature.ui.rides

import com.jakewharton.rxbinding2.view.RxView.clicks
import com.wiscosoft.ridefree.R.drawable.add_drawable
import com.wiscosoft.ridefree.R.layout.fragment_list
import com.wiscosoft.ridefree.core.app.randomId
import com.wiscosoft.ridefree.core.app.showError
import com.wiscosoft.ridefree.core.app.withModels
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Layout
import com.wiscosoft.ridefree.core.base.Title
import com.wiscosoft.ridefree.core.base.goTo
import com.wiscosoft.ridefree.databinding.FragmentListBinding
import com.wiscosoft.ridefree.domain.ride.Ride
import com.wiscosoft.ridefree.titleTextCard
import javax.inject.Inject

@Title("Rides")
@Layout(fragment_list)
class RideListFragment : BaseFragment<FragmentListBinding>() {

  @Inject lateinit var vm: RidesVM

  override fun onReady() {
    super.onReady()
    setupAddButton()
    showRides()
  }

  private fun setupAddButton() {
    binding.fab.setImageResource(add_drawable)
    sub.add(clicks(binding.fab)
      .subscribe { goTo(RideAddFragment()) }
    )
  }

  private fun showRides() {
    sub.add(vm.getAllRides(10, 0)
      .subscribe(
        { if (it.isEmpty()) emptyState() else contentState(it) },
        this::showError)
    )
  }

  private fun emptyState() {
    binding.recyclerView.withModels {
      titleTextCard {
        title("We Have No Rides on Record For You")
        text("Tap the plus button to add a ride!")
        id(randomId())
      }
    }
  }

  private fun contentState(rides: List<Ride>) {
    binding.recyclerView.withModels { rides.forEach { ride ->

      titleTextCard {
        click { _ -> goTo(RideInfoFragment().apply { id = ride.id }) }
        text("Click for more info")
        title(ride.restaurantId)
        id(ride.id)
      }
    }}
  }

}
