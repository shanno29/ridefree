package com.wiscosoft.ridefree.core.base

import com.wiscosoft.ridefree.R
import com.wiscosoft.ridefree.core.test.BaseRoboTest
import com.wiscosoft.ridefree.core.test.TestActivity
import com.wiscosoft.ridefree.core.test.TestFragment
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.fail
import org.junit.Test
import java.lang.IllegalStateException

class BaseFragmentTest : BaseRoboTest() {

  @Test
  fun testFragmentInit() {
    val fragment = TestFragment()

    try { fragment.binding; fail("Should throw IllegalStateException") }
    catch(e: IllegalStateException) { }
  }

  @Test
  fun testFragmentCreated() {
    val fragment = initFragment(TestFragment(), TestActivity::class.java)

    assertNotNull(fragment.loadingUI)
    assertEquals(0, fragment.sub.size())
    assertEquals("TestTitle", fragment.title.text)
    assertEquals(R.layout.fragment_test, fragment.layout.res)
  }

  @Test
  fun testFragmentDestroyed() {
    val fragment = initFragment(TestFragment(), TestActivity::class.java)
    assertEquals(0, fragment.sub.size())

    fragment.sub.add(Observable.empty<String>().subscribe())
    assertEquals(1, fragment.sub.size())

    fragment.onDestroy()
    assertEquals(0, fragment.sub.size())
  }

}
