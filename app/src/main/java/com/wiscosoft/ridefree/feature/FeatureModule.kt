package com.wiscosoft.ridefree.feature

import com.github.salomonbrys.kodein.Kodein
import com.wiscosoft.ridefree.feature.activity.ActivityModule
import com.wiscosoft.ridefree.feature.fragment.FragmentModule

class FeatureModule {
  val bind = Kodein.Module {
    import(ActivityModule().bind)
    import(FragmentModule().bind)
  }

}
