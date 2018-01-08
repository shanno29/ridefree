package com.wiscosoft.ridefree.provider.redux

import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.provider.gps.Pos
import org.junit.Assert.*
import org.junit.Test

class StateTest {

  @Test
  fun testDefault() {
    val default = State.DEFAULT

    assertEquals(0, default.id)
    assertFalse(default.auth.flag)
    assertEquals(User.DEFAULT, default.user)
    assertEquals(Pos.DEFAULT, default.pos)
  }

  @Test
  fun testDestructor() {
    val default = State.DEFAULT
    val (id, auth, user, position) = default

    assertEquals(default.id, id)
    assertEquals(default.auth, auth)
    assertEquals(default.user, user)
    assertEquals(default.pos, position)
  }

  @Test
  fun testClone() {
    val default = State.DEFAULT
    val clone = default.copy(id = 1)

    assertFalse(default.equals(clone))
    assertNotEquals(default.id, clone.id)
    assertNotEquals(default.toString(), clone.toString())
    assertNotEquals(default.hashCode(), clone.hashCode())
  }

}
