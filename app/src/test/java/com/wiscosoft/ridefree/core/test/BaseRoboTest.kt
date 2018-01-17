package com.wiscosoft.ridefree.core.test

import android.app.Service
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.wiscosoft.ridefree.core.app.App
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment

@Config(sdk = [25], packageName = "com.wiscosoft.ridefree", application = App::class)
@RunWith(RobolectricTestRunner::class )
abstract class BaseRoboTest {

  fun <X: Fragment, Y: AppCompatActivity> initFragment(fragment: X, activity: Class<Y>): X {
    startFragment(fragment, activity)
    return fragment
  }

  fun <X: AppCompatActivity> initActivity(activity: Class<X>): X {
    return Robolectric.setupActivity(activity)
  }

  fun <X: Service> initService(service: Class<X>): X {
    return Robolectric.setupService(service)
  }

}