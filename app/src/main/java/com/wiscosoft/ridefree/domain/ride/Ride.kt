package com.wiscosoft.ridefree.domain.ride

import android.arch.persistence.room.Entity
import com.wiscosoft.ridefree.domain.ride.status.Status
import java.util.Date

@Entity(primaryKeys = ["id"])
data class Ride(
  val id: Int,
  val driver: Int,
  val status: Status,
  val dateSubmitted: Date?,
  val dateComplete: Date?,
  val username: String,
  val pickup: String,
  val currentAddress: String,
  val restaurantId: String,
  val notes: String
) {
  companion object {
    val default = Ride(0, 0, Status.UNKNOWN, null, null, "", "", "", "", "")
  }
}