package com.wiscosoft.ridefree.domain.place

import org.junit.Assert.*
import org.junit.Test

class PlaceTest {

  @Test
  fun testDefault() {
    val default = Place.DEFAULT

    assertEquals(0, default.id)
    assertEquals("", default.name)
    assertEquals("", default.info)
    assertEquals("", default.imageUrl)
  }

  @Test
  fun testDestructor() {
    val default = Place.DEFAULT
    val (id, name, info, imageUrl) = default

    assertEquals(default.id, id)
    assertEquals(default.name, name)
    assertEquals(default.info, info)
    assertEquals(default.imageUrl, imageUrl)
  }
  
  @Test
  fun testClone() {
    val default = Place.DEFAULT
    val clone = default.copy(id = 1)

    assertFalse(default.equals(clone))
    assertNotEquals(default.id, clone.id)
    assertNotEquals(default.toString(), clone.toString())
    assertNotEquals(default.hashCode(), clone.hashCode())
  }

}