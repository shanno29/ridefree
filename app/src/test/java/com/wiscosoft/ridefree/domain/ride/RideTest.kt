package com.wiscosoft.ridefree.domain.ride

import com.wiscosoft.ridefree.domain.status.Status
import org.junit.Assert.*
import org.junit.Test
import java.util.Date

class RideTest {

  @Test
  fun testDefault() {
    val default = Ride.DEFAULT

    assertEquals(0, default.id)
    assertEquals(0, default.driver)
    assertEquals(Status.UNKNOWN, default.status)
    assertEquals(Date(0), default.dateSubmitted)
    assertEquals(Date(0), default.dateCompleted)
    assertEquals("", default.username)
    assertEquals("", default.pickup)
    assertEquals("", default.currentAddress)
    assertEquals("", default.restaurantId)
    assertEquals("", default.notes)
  }

  @Test
  fun testDestructor() {
    val default = Ride.DEFAULT
    val (id, driver, status, dateSubmitted, dateCompleted, username, pickup, currentAddress, restaurantId, notes) = default

    assertEquals(default.id, id)
    assertEquals(default.driver, driver)
    assertEquals(default.status, status)
    assertEquals(default.dateSubmitted, dateSubmitted)
    assertEquals(default.dateCompleted, dateCompleted)
    assertEquals(default.username, username)
    assertEquals(default.pickup, pickup)
    assertEquals(default.currentAddress, currentAddress)
    assertEquals(default.restaurantId, restaurantId)
    assertEquals(default.notes, notes)
  }

  @Test
  fun testClone() {
    val default = Ride.DEFAULT
    val clone = default.copy(id = 1)

    assertFalse(default.equals(clone))
    assertNotEquals(default.id, clone.id)
    assertNotEquals(default.toString(), clone.toString())
    assertNotEquals(default.hashCode(), clone.hashCode())
  }

}