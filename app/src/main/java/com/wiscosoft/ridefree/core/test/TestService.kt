package com.wiscosoft.ridefree.core.test

import com.wiscosoft.ridefree.core.app.debugLog
import com.wiscosoft.ridefree.core.base.BaseService
import dagger.Module

class TestService : BaseService() {

  override fun onReady() {
    super.onReady()
    debugLog("TestService Ready")
    /* Later Implementation */
  }

}

@Module
abstract class TestServiceModule {

  @Module
  companion object {

  }

}
