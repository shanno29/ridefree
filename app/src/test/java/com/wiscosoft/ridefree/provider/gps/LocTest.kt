package com.wiscosoft.ridefree.provider.gps

import android.location.Address
import android.location.Location
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.wiscosoft.ridefree.core.test.BaseTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class LocTest : BaseTest() {

  @Test
  fun testDefault() {
    val default = Loc.DEFAULT

    assertEquals(0, default.id)
    assertEquals(0.0, default.lat, 0.0)
    assertEquals(0.0, default.lon, 0.0)
  }

  @Test
  fun testDestructor() {
    val default = Loc.DEFAULT
    val (id, lat, lon) = default

    assertEquals(default.id, id)
    assertEquals(default.lat, lat, 0.0)
    assertEquals(default.lon, lon, 0.0)
  }

  @Test
  fun testClone() {
    val default = Loc.DEFAULT
    val clone = default.copy(id = 1)

    assertTrue(cloneHelper(default, clone))
  }

  @Test
  fun testMiddleOfUSA() {
    val middleOfUSA = Loc.MIDDLE_OF_USA

    assertEquals(0, middleOfUSA.id)
    assertEquals(40.0, middleOfUSA.lat, 0.0)
    assertEquals(100.0, middleOfUSA.lon, 0.0)
  }

  @Test
  fun testFromLocation() {
    val location: Location = mock {
      on { latitude } doReturn 1.0
      on { longitude } doReturn 2.0
    }

    val pos = Loc.fromLocation(location)

    assertEquals(1.0, pos.lat, 0.0)
    assertEquals(2.0, pos.lon, 0.0)
  }

  @Test
  fun testFromAddress() {
    val address: Address = mock {
      on { latitude } doReturn 1.0
      on { longitude } doReturn 2.0
    }

    val pos = Loc.fromAddress(address)

    assertEquals(1.0, pos.lat, 0.0)
    assertEquals(2.0, pos.lon, 0.0)
  }


}