package com.wiscosoft.ridefree.feature.view

import com.wiscosoft.ridefree.core.app.randomId

data class EmptyVM(val id: Long, val title: String, val text: String) {
  companion object {
    val DEFAULT = EmptyVM(randomId(), "emptyTitle", "emptyText")
  }
}