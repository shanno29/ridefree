package com.wiscosoft.ridefree.domain.user.api

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.wiscosoft.ridefree.core.base.BaseDao
import com.wiscosoft.ridefree.domain.user.User
import io.reactivex.Flowable

// STORAGE
@Dao
interface UserStorage : BaseDao<User> {

  @Query("SELECT * FROM user WHERE id = :id")
  fun get(id: Int): Flowable<User>
}