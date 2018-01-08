package com.wiscosoft.ridefree.domain.payment

import org.junit.Assert.*
import org.junit.Test

class PaymentTest {

  @Test
  fun testDefault() {
    val default = Payment.DEFAULT

    assertEquals(0, default.id)
    assertEquals("", default.cCNumber)
    assertEquals("", default.cVV2Number)
    assertEquals("", default.cCExpiration)
    assertEquals("", default.phoneNumber)
  }

  @Test
  fun testDestructor() {
    val default = Payment.DEFAULT
    val (id, cCNumber, cVV2Number, cCExpiration, phoneNumber) = default

    assertEquals(default.id, id)
    assertEquals(default.cCNumber, cCNumber)
    assertEquals(default.cVV2Number, cVV2Number)
    assertEquals(default.cCExpiration, cCExpiration)
    assertEquals(default.phoneNumber, phoneNumber)
  }

  @Test
  fun testClone() {
    val default = Payment.DEFAULT
    val clone = default.copy(
      id = 1,
      cCNumber = "A",
      cVV2Number = "B",
      cCExpiration = "C",
      phoneNumber = "D"
    )

    assertFalse(default == clone)
    assertNotEquals(default.id, clone.id)
    assertNotEquals(default.cCNumber, clone.cCNumber)
    assertNotEquals(default.cVV2Number, clone.cVV2Number)
    assertNotEquals(default.cCExpiration, clone.cCExpiration)
    assertNotEquals(default.phoneNumber, clone.phoneNumber)
    assertNotEquals(default.toString(), clone.toString())
    assertNotEquals(default.hashCode(), clone.hashCode())
  }
  
}