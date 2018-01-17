package com.wiscosoft.ridefree.feature.ui.rides

import com.wiscosoft.ridefree.core.test.BaseRoboTest
import org.junit.Assert
import org.junit.Test

class RideAddFragmentTest : BaseRoboTest() {

  @Test
  fun testFragmentInit() {
    Assert.assertNotNull(RideAddFragment())
  }

}
