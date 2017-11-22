package com.wiscosoft.ridefree.feature.fragment.promos

import com.github.salomonbrys.kodein.instance
import com.wiscosoft.ridefree.R.layout.fragment_list
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.extensions.withModels
import com.wiscosoft.ridefree.databinding.FragmentListBinding
import com.wiscosoft.ridefree.titleTextCard

@Config(title = "Promos", layout = fragment_list)
class PromosFragment : BaseFragment<FragmentListBinding>() {

  private val vm: PromosVM by injector.instance()

  override fun onReady() {
    onEmpty()
  }

  private fun onEmpty() {
    binding.recyclerView.withModels {
      titleTextCard {
        title(vm.title)
        text(vm.text)
        id(vm.id)
      }
    }
  }

}