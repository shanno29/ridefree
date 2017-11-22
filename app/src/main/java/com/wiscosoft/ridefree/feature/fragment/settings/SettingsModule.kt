package com.wiscosoft.ridefree.feature.fragment.settings

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.wiscosoft.ridefree.provider.prefs.Prefs
import com.wiscosoft.ridefree.provider.repo.Repo

class SettingsModule {

  val bind = Kodein.Module {
    bind() from provider { vm(instance(), instance()) }
  }

  private fun vm(repo: Repo, prefs: Prefs) = SettingsVM(repo, prefs)

}