package com.wiscosoft.ridefree.domain.user

import android.arch.persistence.room.Entity

@Entity(primaryKeys = ["id"])
data class User(
  val id: Int,
  val email: String,
  val userName: String,
  val passWord: String,
  val rides: List<Int>,
  val type: List<String>,
  val payments: List<Int>
) {
  companion object {
    val DEFAULT = User(0, "", "", "", emptyList(), emptyList(), emptyList())
  }
}

