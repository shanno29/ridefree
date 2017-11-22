package com.wiscosoft.ridefree.feature.activity.main

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.wiscosoft.ridefree.provider.prefs.Prefs
import com.wiscosoft.ridefree.provider.repo.Repo

class MainModule {

  val bind = Kodein.Module {
    bind() from singleton { vm(instance(), instance()) }
  }

  private fun vm(repo: Repo, prefs: Prefs) = MainVM(repo, prefs)

}
