package com.wiscosoft.ridefree.provider

import com.github.salomonbrys.kodein.Kodein
import com.wiscosoft.ridefree.provider.api.apiModule
import com.wiscosoft.ridefree.provider.gps.gpsModule
import com.wiscosoft.ridefree.provider.permissions.permissionsModule
import com.wiscosoft.ridefree.provider.prefs.prefsModule
import com.wiscosoft.ridefree.provider.push.pushModule
import com.wiscosoft.ridefree.provider.redux.reduxModule
import com.wiscosoft.ridefree.provider.router.routerModule

val providerModule = Kodein.Module {

  import(permissionsModule)
  //import(storageModule)
  //import(networkModule)
  import(routerModule)
  import(prefsModule)
  import(reduxModule)
  import(pushModule)
  import(gpsModule)
  import(apiModule)

}