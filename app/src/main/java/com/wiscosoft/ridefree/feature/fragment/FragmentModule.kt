package com.wiscosoft.ridefree.feature.fragment

import com.github.salomonbrys.kodein.Kodein
import com.wiscosoft.ridefree.feature.fragment.about.AboutModule
import com.wiscosoft.ridefree.feature.fragment.account.AccountModule
import com.wiscosoft.ridefree.feature.fragment.history.HistoryModule
import com.wiscosoft.ridefree.feature.fragment.map.MapModule
import com.wiscosoft.ridefree.feature.fragment.payments.PaymentsModule
import com.wiscosoft.ridefree.feature.fragment.promos.PromosModule
import com.wiscosoft.ridefree.feature.fragment.settings.SettingsModule

class FragmentModule {
  val bind = Kodein.Module {
    import(AboutModule().bind)
    import(AccountModule().bind)
    import(HistoryModule().bind)
    import(MapModule().bind)
    import(PaymentsModule().bind)
    import(PromosModule().bind)
    import(SettingsModule().bind)
  }

}
