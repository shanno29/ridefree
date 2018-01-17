package com.wiscosoft.ridefree.feature.ui.promos

import com.wiscosoft.ridefree.core.test.BaseRoboTest
import org.junit.Assert
import org.junit.Test

class PromosFragmentTest: BaseRoboTest() {

  @Test
  fun testFragmentInit() {
    Assert.assertNotNull(PromosFragment())
  }

}
