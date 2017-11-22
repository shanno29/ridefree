package com.wiscosoft.ridefree.provider

import com.github.salomonbrys.kodein.Kodein
import com.wiscosoft.ridefree.provider.gps.GpsModule
import com.wiscosoft.ridefree.provider.network.NetworkModule
import com.wiscosoft.ridefree.provider.permission.PermissionsModule
import com.wiscosoft.ridefree.provider.prefs.PrefsModule
import com.wiscosoft.ridefree.provider.repo.RepoModule
import com.wiscosoft.ridefree.provider.router.RouterModule
import com.wiscosoft.ridefree.provider.storage.StorageModule

class ProviderModule {
  val bind = Kodein.Module {
    import(PermissionsModule().bind)
    import(NetworkModule().bind)
    import(StorageModule().bind)
    import(RouterModule().bind)
    import(PrefsModule().bind)
    import(GpsModule().bind)
    import(RepoModule().bind)
  }
}
