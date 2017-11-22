package com.wiscosoft.ridefree.domain.user

import android.arch.persistence.room.Entity

@Entity(primaryKeys = arrayOf("id"))
class User {

  var id: Int = 0
  var userName: String = ""
  var fullName: String = ""
  var passWord: String = ""
  var imageUrl: String = ""
  var address: String = ""
  var email: String = ""
  var locX: Double = 0.0
  var locY: Double = 0.0
  var type: List<String>? = null
  var payments: List<Int>? = null
  var rides: List<Int>? = null

}