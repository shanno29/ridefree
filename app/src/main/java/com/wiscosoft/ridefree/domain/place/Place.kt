package com.wiscosoft.ridefree.domain.place

import android.arch.persistence.room.Entity

@Entity(primaryKeys = ["id"])
data class Place(
  val id: Int,
  val name: String,
  val info: String,
  val imageUrl: String
) {
  companion object {
    val default = Place(0, "", "", "")
  }
}



