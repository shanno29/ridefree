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
  var address: String = ""
  var pickup: String = ""
  var destination: String = ""
  var currentAddress: String = ""
  var notes: String = ""

}