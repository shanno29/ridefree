package com.wiscosoft.ridefree.provider.push

import com.wiscosoft.ridefree.core.test.BaseTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class EventTest : BaseTest() {

  @Test
  fun testDefault() {
    val default = Event.DEFAULT

    assertEquals(0, default.statusCode)
    assertEquals("", default.reason)
  }

  @Test
  fun testDestructor() {
    val default = Event.DEFAULT
    val (statusCode, reason) = default

    assertEquals(default.statusCode, statusCode)
    assertEquals(default.reason, reason)
  }

  @Test
  fun testClone() {
    val default = Event.DEFAULT
    val clone = default.copy(statusCode = 1)

    assertTrue(cloneHelper(default, clone))
  }

}