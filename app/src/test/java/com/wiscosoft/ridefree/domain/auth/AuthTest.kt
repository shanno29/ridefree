package com.wiscosoft.ridefree.domain.auth

import org.junit.Assert.*
import org.junit.Test

class AuthTest {

  @Test
  fun testDefault() {
    val default = Auth.DEFAULT

    assertFalse(default.flag)
  }

  @Test
  fun testDestructor() {
    val default = Auth.DEFAULT
    val (flag) = default

    assertEquals(default.flag, flag)
  }

  @Test
  fun testClone() {
    val default = Auth.DEFAULT
    val clone = default.copy(flag = true)

    //assertFalse(default.equals(null))
    assertFalse(default.equals(clone))

    assertFalse(default.hashCode().equals(clone.hashCode()))
    assertNotEquals(default.flag, clone.flag)
    assertNotEquals(default.toString(), clone.toString())
    assertNotEquals(default.hashCode(), clone.hashCode())
  }
  
}