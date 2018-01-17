package com.wiscosoft.ridefree.feature.ui.about

import com.wiscosoft.ridefree.core.test.BaseRoboTest
import org.junit.Assert
import org.junit.Test

class AboutFragmentTest : BaseRoboTest() {

  @Test
  fun testFragmentInit() {
    Assert.assertNotNull(AboutFragment())
  }

}