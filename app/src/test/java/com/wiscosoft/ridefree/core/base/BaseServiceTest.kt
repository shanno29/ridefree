package com.wiscosoft.ridefree.core.base

import com.wiscosoft.ridefree.core.test.BaseRoboTest
import com.wiscosoft.ridefree.core.test.TestService
import dagger.android.DaggerService.*
import io.reactivex.Flowable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.robolectric.Robolectric

class BaseServiceTest : BaseRoboTest() {

  @Test
  fun testServiceInit() {
    val service = Robolectric.buildService(TestService::class.java).get()

    assertNotNull(service.sub)
  }

  @Test
  fun testServiceCreated() {
    val service = initService(TestService::class.java)

    assertEquals(0, service.sub.size())

    val command = service.onStartCommand(null, 0, 0)
    assertEquals(START_STICKY, command)

    val bind = service.onBind(null)
    assertEquals(bind, null)
  }

  @Test
  fun testServiceDestroyed() {
    val service = initService(TestService::class.java)
    assertEquals(0, service.sub.size())

    service.sub.add(Flowable.empty<String>().subscribe())
    assertEquals(1, service.sub.size())

    service.onDestroy()
    assertEquals(0, service.sub.size())
  }

}