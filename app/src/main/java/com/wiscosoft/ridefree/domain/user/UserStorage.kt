package com.wiscosoft.ridefree.domain.user

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface UserStorage {

  @Query("SELECT * FROM user WHERE id = :id")
  fun get(id: Int): Flowable<User>

  @Insert(onConflict = REPLACE)
  fun add(user: User): Long

  @Insert(onConflict = REPLACE)
  fun modify(user: User): Long

  @Delete
  fun delete(user: User): Int

}
