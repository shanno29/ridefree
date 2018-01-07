package com.wiscosoft.ridefree.core.base

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Update

interface BaseDao<in Type> {

  @Insert(onConflict = REPLACE)
  fun add(type: Type)

  @Update
  fun modify(type: Type)

  @Delete
  fun delete(type: Type)
}