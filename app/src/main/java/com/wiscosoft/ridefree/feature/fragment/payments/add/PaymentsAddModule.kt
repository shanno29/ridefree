package com.wiscosoft.ridefree.feature.fragment.payments.add

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.wiscosoft.ridefree.provider.repo.Repo

class PaymentsAddModule {

  val bind = Kodein.Module {
    bind() from provider { vm(instance()) }
  }

  private fun vm(repo: Repo): PaymentsAddVM = PaymentsAddVM(repo)

}