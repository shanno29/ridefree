package com.wiscosoft.ridefree.feature.ui.splash

import com.wiscosoft.ridefree.core.test.BaseRoboTest
import org.junit.Assert
import org.junit.Test

class SplashFragmentTest : BaseRoboTest() {

  @Test
  fun testFragmentInit() {
    Assert.assertNotNull(SplashFragment())
  }

}