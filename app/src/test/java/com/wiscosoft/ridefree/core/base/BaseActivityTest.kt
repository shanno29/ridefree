package com.wiscosoft.ridefree.core.base

import com.wiscosoft.ridefree.R
import com.wiscosoft.ridefree.core.test.BaseRoboTest
import com.wiscosoft.ridefree.core.test.TestActivity
import com.wiscosoft.ridefree.core.test.TestFragment
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test
import org.robolectric.Robolectric.buildActivity
import java.lang.IllegalStateException

class BaseActivityTest : BaseRoboTest() {

  @Test
  fun testActivityInit() {
    val activity = buildActivity(TestActivity::class.java).get()

    try { activity.binding;fail("Should throw IllegalStateException") }
    catch (e: IllegalStateException) { }
  }

  @Test
  fun testActivityCreated() {
    val activity = initActivity(TestActivity::class.java)

    assertEquals(0, activity.sub.size())
    assertEquals(R.layout.activity_test, activity.layout.res)
  }


  @Test
  fun testActivityDestroyed() {
    val activity = initActivity(TestActivity::class.java)
    assertEquals(0, activity.sub.size())

    activity.sub.add(Observable.empty<String>().subscribe())
    assertEquals(1, activity.sub.size())

    activity.onDestroy()
    assertEquals(0, activity.sub.size())
  }

//  @Test
//  fun testActivityTitleUpdate() {
//    val activity = initActivity(TestActivity::class.java)
//    assertEquals(0, activity.sub.size())
//    assertEquals("RideFree", activity.title)
//    assertEquals(0, activity.backStackSize())
//
//    activity.titleUpdates()
//    assertEquals(1, activity.sub.size())
//    assertEquals("RideFree", activity.title)
//    assertEquals(0, activity.backStackSize())
//
//    activity.goTo(TestFragment())
//    assertEquals(1, activity.sub.size())
//    assertEquals("TestTitle", activity.title)
//    assertEquals(1, activity.backStackSize())
//  }

}
