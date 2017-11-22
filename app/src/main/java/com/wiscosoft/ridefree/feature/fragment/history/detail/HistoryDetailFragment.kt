package com.wiscosoft.ridefree.feature.fragment.history.detail

import com.github.salomonbrys.kodein.instance
import com.wiscosoft.ridefree.R.layout.fragment_list
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.extensions.showError
import com.wiscosoft.ridefree.core.extensions.withModels
import com.wiscosoft.ridefree.databinding.FragmentListBinding
import com.wiscosoft.ridefree.domain.ride.Ride
import com.wiscosoft.ridefree.titleTextCard

@Config(title = "Detail", layout = fragment_list)
class HistoryDetailFragment : BaseFragment<FragmentListBinding>() {

  private val vm: HistoryDetailVM by injector.instance()
  internal var rideId: Int = 0

  override fun onReady() {
    showRide()
  }

  private fun showRide() {
    sub.add(vm.ride(id).subscribe(
        { onContent(it) },
        { showError(it) }
    ))
  }

  private fun onContent(ride: Ride) {
    binding.recyclerView.withModels {
      titleTextCard {
        title(ride.dateComplete.toString())
        text(ride.toString())
        id(ride.id)
      }
    }
  }

}