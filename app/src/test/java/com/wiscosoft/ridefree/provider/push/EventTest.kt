package com.wiscosoft.ridefree.provider.push

import org.junit.Assert.*
import org.junit.Test

class EventTest {

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

    assertFalse(default.equals(clone))
    assertNotEquals(default.statusCode, clone.statusCode)
    assertNotEquals(default.toString(), clone.toString())
    assertNotEquals(default.hashCode(), clone.hashCode())
  }

}