package com.wiscosoft.ridefree.domain.payment

import android.arch.persistence.room.Entity

@Entity(primaryKeys = arrayOf("id"))
data class Payment(
  val id: Int,
  val cvv: String,
  val ccNumber: String,
  val ccExpiration: String
)

