package com.wiscosoft.ridefree.provider.permissions

import com.apt7.rxpermissions.Permission
import io.reactivex.Observable

interface Permissions {

  val check: (Array<String>) -> Observable<Permission>

  val request: (Array<String>) -> Observable<Permission>

}
