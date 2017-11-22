package com.wiscosoft.ridefree.feature.fragment.account.logout

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.wiscosoft.ridefree.provider.prefs.Prefs
import com.wiscosoft.ridefree.provider.repo.Repo

class LogoutModule {

  val bind = Kodein.Module {
    bind() from provider { vm(instance(), instance()) }
  }

  private fun vm(repo: Repo, prefs: Prefs) = LogoutVM(repo, prefs)

}