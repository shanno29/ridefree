package com.wiscosoft.ridefree.feature.ui.rides

import com.wiscosoft.ridefree.core.test.BaseRoboTest
import com.wiscosoft.ridefree.feature.ui.payments.PaymentListFragment
import org.junit.Assert
import org.junit.Test

class RideListFragmentTest : BaseRoboTest() {

  @Test
  fun testFragmentInit() {
    Assert.assertNotNull(RideListFragment())
  }

}
