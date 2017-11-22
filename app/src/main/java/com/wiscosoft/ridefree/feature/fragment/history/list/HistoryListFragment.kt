package com.wiscosoft.ridefree.feature.fragment.history.list

import com.github.salomonbrys.kodein.instance
import com.wiscosoft.ridefree.R.layout.fragment_list
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.extensions.goTo
import com.wiscosoft.ridefree.core.extensions.showError
import com.wiscosoft.ridefree.core.extensions.withModels
import com.wiscosoft.ridefree.databinding.FragmentListBinding
import com.wiscosoft.ridefree.domain.ride.Ride
import com.wiscosoft.ridefree.provider.router.Router
import com.wiscosoft.ridefree.titleTextCard

@Config(title = "History", layout = fragment_list)
class HistoryListFragment : BaseFragment<FragmentListBinding>() {

  private val vm: HistoryVM by injector.instance()
  private val router: Router by injector.instance()

  override fun onReady() {
    showRides()
  }

  private fun showRides() {
    sub.add(vm.rides().subscribe(
        { onResponse(it) },
        { showError(it) }
    ))
  }

  private fun onResponse(rides: List<Ride>) {
    if (rides.isEmpty()) onEmpty() else onContent(rides)
  }

  private fun onEmpty() {
    binding.recyclerView.withModels {
      titleTextCard {
        title("You have not yet requested any rides")
        id(420)
      }
    }
  }

  private fun onContent(rides: List<Ride>) {
    binding.recyclerView.withModels {
      rides.forEach {
        titleTextCard {
          clickListener { _ -> goTo(router.historyDetail(it.id)) }
          title(it.destination)
          text(it.toString())
          id(it.id)
        }
      }
    }
  }

}