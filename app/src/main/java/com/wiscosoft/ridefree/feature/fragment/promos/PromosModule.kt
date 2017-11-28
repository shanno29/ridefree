package com.wiscosoft.ridefree.feature.fragment.promos

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.wiscosoft.ridefree.core.base.EmptyListVM

class PromosModule {

  val bind = Kodein.Module {
    bind<EmptyListVM>("promos") with provider { emptyContentVM() }
    bind<PromosVM>() with provider { vm(instance("promos")) }
  }

  private fun emptyContentVM(): EmptyListVM {
    return EmptyListVM().apply {
      title = "Coming Soon"
      text = "Check back in a future update!"
    }

  }

  private fun vm(emptyListVM: EmptyListVM): PromosVM = PromosVM(emptyListVM)

}