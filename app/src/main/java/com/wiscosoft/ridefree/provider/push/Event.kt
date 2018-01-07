package com.wiscosoft.ridefree.provider.push

data class Event(val statusCode: Int, val reason: String) {
  companion object {
    val default = Event(0, "")
  }
}