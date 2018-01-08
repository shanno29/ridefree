package com.wiscosoft.ridefree.domain.status

import android.arch.persistence.room.TypeConverter

class StatusConverters {

  @TypeConverter
  fun statusFromInt(value: Int): Status = when (value) {
    -1 -> Status.UNKNOWN
    0 -> Status.PENDING
    1 -> Status.ACCEPTED
    2 -> Status.IN_PROGRESS
    3 -> Status.COMPLETED
    4 -> Status.CANCELLED
    else -> Status.UNKNOWN
  }

  @TypeConverter
  fun intFromStatus(status: Status): Int = when (status) {
    Status.PENDING -> 0
    Status.ACCEPTED -> 1
    Status.IN_PROGRESS -> 2
    Status.COMPLETED -> 3
    Status.CANCELLED -> 4
    else -> -1
  }

}