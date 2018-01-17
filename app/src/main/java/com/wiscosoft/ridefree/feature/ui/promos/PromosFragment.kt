package com.wiscosoft.ridefree.feature.ui.promos

import com.wiscosoft.ridefree.R.layout.fragment_list
import com.wiscosoft.ridefree.core.app.withModels
import com.wiscosoft.ridefree.databinding.FragmentListBinding
import com.wiscosoft.ridefree.titleTextCard
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Layout
import com.wiscosoft.ridefree.core.base.Title
import javax.inject.Inject

@Title("Promos")
@Layout(fragment_list)
class PromosFragment : BaseFragment<FragmentListBinding>() {

  @Inject lateinit var vm: PromosVM

  override fun onReady() {
    super.onReady()
    emptyState()
  }

  fun emptyState() {
    binding.recyclerView.withModels {
      titleTextCard {
       title(vm.empty.title)
       text(vm.empty.text)
       id(vm.empty.id)
      }
    }
  }

}


