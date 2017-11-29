package com.wiscosoft.ridefree.feature

import com.github.salomonbrys.kodein.Kodein
import com.wiscosoft.ridefree.core.main.MainModule
import com.wiscosoft.ridefree.feature.about.AboutModule
import com.wiscosoft.ridefree.feature.account.AccountModule
import com.wiscosoft.ridefree.feature.map.MapModule
import com.wiscosoft.ridefree.feature.payments.PaymentsModule
import com.wiscosoft.ridefree.feature.promos.PromosModule
import com.wiscosoft.ridefree.feature.rides.RidesModule
import com.wiscosoft.ridefree.feature.settings.SettingsModule
import com.wiscosoft.ridefree.feature.splash.SplashModule

class FeatureModule {
  val bind = Kodein.Module {
    import(MainModule().bind)
    import(SplashModule().bind)
    import(AboutModule().bind)
    import(AccountModule().bind)
    import(RidesModule().bind)
    import(MapModule().bind)
    import(PaymentsModule().bind)
    import(PromosModule().bind)
    import(SettingsModule().bind)
  }

}
