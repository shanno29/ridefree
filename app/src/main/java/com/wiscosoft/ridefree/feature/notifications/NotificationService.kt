package com.wiscosoft.ridefree.feature.notifications

import com.wiscosoft.ridefree.core.app.debugLog
import com.wiscosoft.ridefree.core.app.errorLog
import com.wiscosoft.ridefree.core.base.BaseService
import javax.inject.Inject

class NotificationService : BaseService() {

  @Inject lateinit var vm: NotificationVM

  override fun onReady() {
    super.onReady()
    vm.startPush
    locationUpdates()
  }

  private fun locationUpdates() {
    sub.add(vm.locationUpdates()
      .subscribe(this::debugLog, this::errorLog)
    )
  }

  override fun onDestroy() {
    vm.stopPush
    super.onDestroy()
  }

}
