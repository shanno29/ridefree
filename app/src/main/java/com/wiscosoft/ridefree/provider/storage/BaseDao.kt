package com.wiscosoft.ridefree.provider.storage

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Update

interface BaseDao<in Type> {

  @Insert(onConflict = REPLACE)
  fun add(type: Type): Long

  @Update
  fun modify(type: Type): Int

  @Delete
  fun delete(type: Type): Int

}