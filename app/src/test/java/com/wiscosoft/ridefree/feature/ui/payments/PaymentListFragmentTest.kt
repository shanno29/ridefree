package com.wiscosoft.ridefree.feature.ui.payments

import com.wiscosoft.ridefree.core.test.BaseRoboTest
import org.junit.Assert
import org.junit.Test

class PaymentListFragmentTest : BaseRoboTest() {

  @Test
  fun testFragmentInit() {
    Assert.assertNotNull(PaymentListFragment())
  }

}
