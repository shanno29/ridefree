package com.wiscosoft.ridefree.domain.status

import android.arch.persistence.room.TypeConverter

enum class Status {
  PENDING,
  ACCEPTED,
  IN_PROGRESS,
  COMPLETED,
  CANCELLED,
  UNKNOWN;

}


