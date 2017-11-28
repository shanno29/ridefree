package com.wiscosoft.ridefree.domain.payment

import android.arch.persistence.room.Entity
import com.wiscosoft.ridefree.core.extensions.randomId

@Entity(primaryKeys = arrayOf("id"))
class Payment {
  var id: Int = randomId()
  var cCNumber: String = ""
  var cVV2Number: String = ""
  var cCExpiration: String = ""
  var phoneNumber: String = ""

  override fun toString(): String {
    var res = ""
    res += "\n ID: \t $id"
    res += "\n CC#: \t $cCNumber"
    res += "\n CVV2#: \t $cVV2Number"
    res += "\n Expiration: \t $cCExpiration"
    res += "\n Phone Number: \t $phoneNumber"
    return res
  }
}

