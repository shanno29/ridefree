package com.wiscosoft.ridefree.feature.ui.payments

import com.wiscosoft.ridefree.core.test.BaseRoboTest
import com.wiscosoft.ridefree.feature.ui.promos.PromosFragment
import org.junit.Assert
import org.junit.Test

class PaymentAddFragmentTest : BaseRoboTest() {

  @Test
  fun testFragmentInit() {
    Assert.assertNotNull(PaymentAddFragment())
  }

}
