package com.wiscosoft.ridefree.domain.user

import org.junit.Assert.*
import org.junit.Test

class UserTest {

  @Test
  fun testDefault() {
    val default = User.DEFAULT
    assertEquals(0, default.id)
    assertEquals("", default.email)
    assertEquals("", default.userName)
    assertEquals("", default.passWord)
    assertEquals(emptyList<Int>(), default.rides)
    assertEquals(emptyList<String>() ,default.type)
    assertEquals(emptyList<Int>(), default.payments)
  }

  @Test
  fun testDestructor() {
    val default = User.DEFAULT
    val (id, email, userName, passWord, rides, type, payments) = default

    assertEquals(default.id, id)
    assertEquals(default.email, email)
    assertEquals(default.userName, userName)
    assertEquals(default.passWord, passWord)
    assertEquals(default.rides, rides)
    assertEquals(default.type, type)
    assertEquals(default.payments, payments)
  }

  @Test
  fun testClone() {
    val default = User.DEFAULT
    val clone = default.copy(id = 1)

    assertFalse(default.equals(clone))
    assertNotEquals(default.id, clone.id)
    assertNotEquals(default.toString(), clone.toString())
    assertNotEquals(default.hashCode(), clone.hashCode())
  }

}