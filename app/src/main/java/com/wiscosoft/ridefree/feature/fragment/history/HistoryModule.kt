package com.wiscosoft.ridefree.feature.fragment.history

import com.github.salomonbrys.kodein.Kodein
import com.wiscosoft.ridefree.feature.fragment.history.detail.HistoryDetailModule
import com.wiscosoft.ridefree.feature.fragment.history.list.HistoryListModule

class HistoryModule {
  val bind = Kodein.Module {
    import(HistoryListModule().bind)
    import(HistoryDetailModule().bind)
  }
}