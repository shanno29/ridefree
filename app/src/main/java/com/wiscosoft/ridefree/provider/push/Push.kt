package com.wiscosoft.ridefree.provider.push

interface Push {

  val setup: () -> Unit

  val openSocket: () -> Unit

  val writeToSocket: (String) -> Unit

  val readFromSocket: ((Event) -> Unit) -> Unit

  val closeSocket: () -> Unit

}