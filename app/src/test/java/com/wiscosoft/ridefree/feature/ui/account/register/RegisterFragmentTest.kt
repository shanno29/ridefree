package com.wiscosoft.ridefree.feature.ui.account.register

import com.wiscosoft.ridefree.core.test.BaseRoboTest
import org.junit.Assert
import org.junit.Test

class RegisterFragmentTest : BaseRoboTest() {

  @Test
  fun testFragmentInit() {
    Assert.assertNotNull(RegisterFragment())
  }

//  @Test
//  fun testFragmentCreated() {
//    val fragment = initFragment(RegisterFragment())
//    Assert.assertNotNull(fragment)
//
//    //Assert.assertEquals("Register", fragment.config.title)
//    //Assert.assertEquals(R.layout.fragment_register, fragment.config.layout)
//    //Assert.assertEquals(2, fragment.sub.size())
//  }

//  @Test
//  fun testLoginButton() {
//    val fragment = initFragment(RegisterFragment())
//    Assert.assertEquals(fragment.sub.size(), 2)
//    Assert.assertEquals(1, fragment.activity.backStack.count())
//
//    fragment.binding.btLogin.performClick()
//    Assert.assertTrue(fragment.sub.size() == 2)
//    Assert.assertEquals(2, fragment.activity.backStack.count())
//  }

}