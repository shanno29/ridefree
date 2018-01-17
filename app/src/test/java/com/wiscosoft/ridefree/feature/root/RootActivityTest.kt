package com.wiscosoft.ridefree.feature.root

import com.wiscosoft.ridefree.core.test.BaseRoboTest
import org.junit.Assert
import org.junit.Test
import org.robolectric.Robolectric

class RootActivityTest : BaseRoboTest() {

  @Test
  fun testActivityInit() {
    val activity = Robolectric.buildActivity(RootActivity::class.java).get()
    Assert.assertNotNull(activity)
  }

}

