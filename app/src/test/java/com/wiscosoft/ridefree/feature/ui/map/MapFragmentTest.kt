package com.wiscosoft.ridefree.feature.ui.map

import com.wiscosoft.ridefree.core.test.BaseRoboTest
import org.junit.Assert
import org.junit.Test

class MapFragmentTest : BaseRoboTest() {

  @Test
  fun testFragmentInit() {
    Assert.assertNotNull(MapFragment())
  }

}