package com.wiscosoft.ridefree.provider.permissions

import com.apt7.rxpermissions.Permission
import io.reactivex.Flowable

interface Permissions {

  fun check(vararg items: String): Flowable<Permission>

  fun request(vararg items: String): Flowable<Permission>
}
