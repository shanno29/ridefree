package com.wiscosoft.ridefree.feature.fragment.promos

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.provider

class PromosModule {

  val bind = Kodein.Module {
    bind() from provider { vm() }
  }

  private fun vm(): PromosVM = PromosVM()

}