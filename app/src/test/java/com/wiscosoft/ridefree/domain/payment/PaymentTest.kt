package com.wiscosoft.ridefree.domain.payment

import com.wiscosoft.ridefree.core.test.BaseTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PaymentTest : BaseTest() {

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
    val clone = default.copy(id = 1)

    assertTrue(cloneHelper(default, clone))
  }

}