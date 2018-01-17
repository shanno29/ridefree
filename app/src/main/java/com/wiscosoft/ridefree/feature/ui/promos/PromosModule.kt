package com.wiscosoft.ridefree.feature.ui.promos

import dagger.Module
import dagger.Provides

@Module
class PromosModule {

  @Provides
  fun promosVM(): PromosVM {
    return PromosVM()
  }

}