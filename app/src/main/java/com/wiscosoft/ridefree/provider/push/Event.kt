package com.wiscosoft.ridefree.provider.push

data class Event(val statusCode: Int, val reason: String) {
  companion object {
    val DEFAULT = Event(0, "")
  }
}