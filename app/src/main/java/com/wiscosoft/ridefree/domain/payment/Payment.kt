package com.wiscosoft.ridefree.domain.payment

import android.arch.persistence.room.Entity

@Entity(primaryKeys = ["id"])
data class Payment(
  val id: Int,
  val cCNumber: String,
  val cVV2Number: String,
  val cCExpiration: String,
  val phoneNumber: String
) {
  companion object {
    val DEFAULT = Payment(0, "", "", "", "")
  }
}
