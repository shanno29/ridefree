package com.wiscosoft.ridefree.feature

import com.github.salomonbrys.kodein.Kodein
import com.wiscosoft.ridefree.feature.about.aboutModule
import com.wiscosoft.ridefree.feature.login.loginModule
import com.wiscosoft.ridefree.feature.logout.logoutModule
import com.wiscosoft.ridefree.feature.main.mainModule
import com.wiscosoft.ridefree.feature.map.mapModule
import com.wiscosoft.ridefree.feature.notification.notificationModule
import com.wiscosoft.ridefree.feature.promos.promosModule
import com.wiscosoft.ridefree.feature.register.registerModule
import com.wiscosoft.ridefree.feature.settings.settingsModule
import com.wiscosoft.ridefree.feature.splash.splashModule

val featureModule = Kodein.Module {

  import(mapModule)
  import(mainModule)
  import(aboutModule)
  import(loginModule)
  import(promosModule)
  import(logoutModule)
  import(splashModule)
  import(registerModule)
  import(settingsModule)

  import(notificationModule)

  //import(RidesModule().bind)
  //import(PaymentsModule().bind)
}