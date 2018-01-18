package com.wiscosoft.ridefree.core.base

import android.app.Activity
import android.support.test.rule.ActivityTestRule
import org.junit.Rule

abstract class BaseUITest<T : Activity>(clazz: Class<T>) {

  @Rule @JvmField val intentsRule = ActivityTestRule(clazz)

  val checkThat: Matchers = Matchers()
  val events: Events = Events()

}