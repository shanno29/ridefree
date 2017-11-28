package com.wiscosoft.ridefree.domain.ride

import android.arch.persistence.room.Entity
import com.wiscosoft.ridefree.domain.ride.status.Status
import java.util.*

@Entity(primaryKeys = arrayOf("id"))
class Ride {

  var id: Int = 0
  var driver: Int = 0
  var status: Status = Status.UNKNOWN
  var dateSubmitted: Date? = null
  var dateComplete: Date? = null
  var username: String = ""
  var pickup: String = ""
  var currentAddress: String = ""
  var restaurantId: String = ""
  var notes: String = ""

  override fun toString(): String {
    var res = "Ride(\n"
    res += "\n Id: \t $id"
    res += "\n Driver: \t $driver"
    res += "\n Status: \t $status"
    res += "\n Date Submitted: \t $dateSubmitted"
    res += "\n Date Completed: \t $dateComplete"
    res += "\n Username: \t $username"
    res += "\n Pickup: \t $pickup"
    res += "\n Current Address: \t $currentAddress"
    res += "\n Restaurant Id: \t $restaurantId"
    res += "\n Notes: \t $notes"
    res += "\n)"
    return res
  }

}
