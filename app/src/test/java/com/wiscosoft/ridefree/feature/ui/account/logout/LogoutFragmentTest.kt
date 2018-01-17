package com.wiscosoft.ridefree.feature.ui.account.logout

import com.wiscosoft.ridefree.core.test.BaseRoboTest
import org.junit.Assert
import org.junit.Test

class LogoutFragmentTest : BaseRoboTest() {

  @Test
  fun testFragmentInit() {
    Assert.assertNotNull(LogoutFragment())
  }

}