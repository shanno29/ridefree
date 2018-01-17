package com.wiscosoft.ridefree.feature.notifications

import com.wiscosoft.ridefree.provider.gps.Gps
import com.wiscosoft.ridefree.provider.gps.Loc
import com.wiscosoft.ridefree.provider.push.Push
import com.wiscosoft.ridefree.provider.redux.Action
import com.wiscosoft.ridefree.provider.redux.State
import com.wiscosoft.ridefree.provider.rx.setThreads
import io.reactivex.Observable
import redux.api.Store

class NotificationVM(gps: Gps, push: Push, store: Store<State>) {

  val startPush: () -> Unit = {
    push.setup()
    push.openSocket()
    push.writeToSocket("") /* TODO token goes here */
    //push.readFromSocket(this::onEvent)
  }

  val stopPush: () -> Unit = {
    push.closeSocket()
  }

  val locationUpdates: () -> Observable<Loc> = {
    gps.location
      .doOnNext { store.dispatch(Action.LocUpdate(it.lat, it.lon)) }
      .setThreads()
  }

}