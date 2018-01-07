package com.wiscosoft.ridefree.provider.push

interface Push {

  fun setup()

  fun openSocket()

  fun writeToSocket(text: String)

  fun readFromSocket(callback: (Event) -> Unit)

  fun closeSocket()
}