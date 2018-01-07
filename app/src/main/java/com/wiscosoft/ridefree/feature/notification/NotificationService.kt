package com.wiscosoft.ridefree.feature.notification

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.wiscosoft.ridefree.core.base.BaseService
import com.wiscosoft.ridefree.core.log
import com.wiscosoft.ridefree.provider.gps.Gps
import com.wiscosoft.ridefree.provider.push.Push
import com.wiscosoft.ridefree.provider.redux.Action
import com.wiscosoft.ridefree.provider.redux.State
import redux.api.Store

class NotificationService : BaseService() {

  private val store: Store<State> by injector.instance()
  private val push: Push by injector.instance()
  private val gps: Gps by injector.instance()

  override fun onCreate() {
    super.onCreate()
    log("onCreate")
    push.setup()
  }

  override fun onReady() {
    log("onReady")
    locationUpdates()
    //push.openSocket()
    //push.writeToSocket(Constants.PushToken)
    //push.readFromSocket(this::onEvent)
  }

  private fun locationUpdates() {
    sub.add(gps.location.subscribe(
      { store.dispatch(Action.LocUpdate(it)) },
      { log(it.localizedMessage) }
    ))
  }

  override fun onDestroy() {
    log("onDestroy")
    super.onDestroy()
    push.closeSocket()
  }

}

val notificationModule = Kodein.Module {}
