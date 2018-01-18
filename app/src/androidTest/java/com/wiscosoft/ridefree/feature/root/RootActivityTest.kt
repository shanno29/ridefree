package com.wiscosoft.ridefree.feature.root

import org.junit.Test
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import com.wiscosoft.ridefree.core.base.BaseUITest
import org.junit.Assert
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class RootActivityTest: BaseUITest<RootActivity>(RootActivity::class.java) {

  @Test
  fun testDefaultTitle() {
    Assert.assertEquals("", intentsRule.activity.title)
  }

}


//  @Test
//  fun shouldOpenHelloWorldScreen() {
//    events.clickOnView(R.id.btn_hello_world)
//    checkThat.nextOpenActivityIs(HelloWorldActivity::class.java)
//  }
//
//  @Test
//  fun shouldDisplayAction() {
//    events.clickOnView(R.id.fab)
//    checkThat.viewIsVisibleAndContainsText(R.string.action)
//  }
