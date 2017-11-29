package com.wiscosoft.ridefree.feature.rides

import com.github.salomonbrys.kodein.instance
import com.jakewharton.rxbinding2.view.RxView.clicks
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.wiscosoft.ridefree.R.drawable.add_24dp
import com.wiscosoft.ridefree.R.layout.fragment_list
import com.wiscosoft.ridefree.TitleTextCardBindingModel_
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.extensions.goTo
import com.wiscosoft.ridefree.core.extensions.showError
import com.wiscosoft.ridefree.databinding.FragmentListBinding
import com.wiscosoft.ridefree.domain.ride.Ride
import com.wiscosoft.ridefree.provider.router.Router

@Config(title = "Rides", layout = fragment_list)
class RidesListFragment : BaseFragment<FragmentListBinding>() {

  private val router: Router by injector.instance()
  private val vm: RidesVM by injector.instance()
  private val img: Int = add_24dp

  override fun onReady() {
    binding.fab.setImageResource(img)
    setupAddButton()
    showRides()
  }

  private fun setupAddButton() {
    clicks(binding.fab).bindToLifecycle(provider)
      .map { router.rideAdd() }
      .subscribe(this::goTo)
  }

  private fun showRides() {
    vm.getAllRides().bindToLifecycle(provider)
      .map { if (it.isEmpty()) emptyState() else contentState(it) }
      .subscribe(binding.recyclerView::setModels, this::showError)
  }

  private fun emptyState() = listOf(
    TitleTextCardBindingModel_().apply {
      title(vm.empty.title)
      text(vm.empty.text)
      id(vm.empty.id)
    }
  )

  private fun contentState(rides: List<Ride>) = rides.map {
    TitleTextCardBindingModel_().apply {
      click { _ -> goTo(router.rideInfo(it.id)) }
      text("Click for more info")
      title(it.restaurantId)
      id(it.id)
    }
  }

}
