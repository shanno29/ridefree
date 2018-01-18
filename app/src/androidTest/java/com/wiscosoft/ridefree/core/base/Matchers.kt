package com.wiscosoft.ridefree.core.base

import android.app.Activity
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.v7.widget.Toolbar
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Description
import org.hamcrest.Matcher

class Matchers {
  fun <T : Activity> nextOpenActivityIs(clazz: Class<T>) {
    intended(IntentMatchers.hasComponent(clazz.name))
  }

  fun viewIsVisibleAndContainsText(@StringRes stringResource: Int) {
    onView(withText(stringResource)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
  }

  fun viewContainsText(@IdRes viewId: Int, @StringRes stringResource: Int) {
    onView(withId(viewId)).check(matches(withText(stringResource)))
  }

  fun matchesToolbarTitle(title: CharSequence): ViewInteraction {
    return onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(`is`(title))))
  }

  private fun withToolbarTitle(textMatcher: Matcher<CharSequence>): Matcher<Any> {

    return object : BoundedMatcher<Any, Toolbar>(Toolbar::class.java) {

      public override fun matchesSafely(toolbar: Toolbar): Boolean {
        return textMatcher.matches(toolbar.title)
      }

      override fun describeTo(description: Description) {
        description.appendText("with toolbar title: ")
        textMatcher.describeTo(description)
      }
    }
  }

}