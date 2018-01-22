package com.wiscosoft.ridefree.feature.root

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.withId
import org.junit.Test
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import com.wiscosoft.ridefree.R
import com.wiscosoft.ridefree.core.base.BaseUITest
import org.junit.Assert
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class RootActivityTest: BaseUITest<RootActivity>(RootActivity::class.java) {

  @Test
  fun testDefaultTitle() {

    /* LoginFragment */
    Assert.assertEquals("Login", intentsRule.activity.title)
    onView(withId(R.id.btRegister)).perform(click())

    /* RegisterFragment */
    Assert.assertEquals("Register", intentsRule.activity.title)


    /* All Blank  */
    onView(withId(R.id.btSubmit)).perform(click())
//    onView(withText("Invalid email Format")).check(matches(isDisplayed()))
//    onView(withText("Invalid username Format")).check(matches(isDisplayed()))
//    onView(withText("This Field can't be empty")).check(matches(isDisplayed()))
//
//    onView(withId(R.id.etEmail))
//      .perform(click())
//      .perform(clearText())
//      .perform(typeText("asd"))
//
//    /* Email less than 4 chars */
//    onView(withId(R.id.btSubmit))


//
//    onView(withId(R.id.btSubmit)).perform(click())
//
//    onView(withId(R.id.etEmail))
//      .perform(click())
//      .perform(clearText())
//      .perform(typeText("testUser@email.com"))
//

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
