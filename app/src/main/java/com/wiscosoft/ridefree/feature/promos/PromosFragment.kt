package com.wiscosoft.ridefree.feature.promos

import com.airbnb.epoxy.DataBindingEpoxyModel
import com.github.salomonbrys.kodein.instance
import com.wiscosoft.ridefree.R.layout.fragment_list
import com.wiscosoft.ridefree.TitleTextCardBindingModel_
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.databinding.FragmentListBinding

@Config(title = "Promos", layout = fragment_list)
class PromosFragment : BaseFragment<FragmentListBinding>() {

  private val vm: PromosVM by injector.instance()

  override fun onReady() {
    showPromos()
  }

  private fun showPromos() {
    binding.recyclerView.setModels(emptyState())
  }

  private fun emptyState(): List<DataBindingEpoxyModel> {
    return listOf(
      TitleTextCardBindingModel_().apply {
        title(vm.empty.title)
        text(vm.empty.text)
        id(vm.empty.id)
      }
    )
  }

}