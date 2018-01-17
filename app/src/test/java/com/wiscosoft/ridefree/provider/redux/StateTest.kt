package com.wiscosoft.ridefree.provider.redux

import com.wiscosoft.ridefree.core.test.BaseTest
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.provider.gps.Loc
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class StateTest : BaseTest() {

  @Test
  fun testDefault() {
    val default = State.DEFAULT

    assertEquals(0, default.id)
    assertFalse(default.auth.flag)
    assertEquals(User.DEFAULT, default.user)
    assertEquals(Loc.DEFAULT, default.loc)
  }

  @Test
  fun testDestructor() {
    val default = State.DEFAULT
    val (id, loc, user, auth) = default


    assertEquals(default.id, id)
    assertEquals(default.loc, loc)
    assertEquals(default.auth, auth)
    assertEquals(default.user, user)
  }

  @Test
  fun testClone() {
    val default = State.DEFAULT
    val clone = default.copy(id = 1)

    assertTrue(cloneHelper(default, clone))
  }

}
