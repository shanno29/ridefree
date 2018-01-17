package com.wiscosoft.ridefree.domain.auth

import com.wiscosoft.ridefree.core.test.BaseTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AuthTest : BaseTest() {

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

    assertTrue(cloneHelper(default, clone))
  }

}