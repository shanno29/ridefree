package com.wiscosoft.ridefree.feature.register

import com.nhaarman.mockito_kotlin.mock
import com.wiscosoft.ridefree.feature.login.LoginVM
import com.wiscosoft.ridefree.provider.api.entity.user.UserApi
import com.wiscosoft.ridefree.provider.redux.State
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import redux.api.Store
import retrofit2.HttpException

class RegisterVMTest {

  @Test
  fun testVM()  {
    val userApi = mock<UserApi>()
    val store = mock<Store<State>>()

    assertNotNull(userApi)
    assertNotNull(store)

    val vm = LoginVM(userApi, store)
    assertNotNull(vm)
  }

  @Test
  fun testCreateUser() {
    val userApi = mock(UserApi::class.java)
    val vm = RegisterVM(userApi)

    val testEmail = "emailOne"
    val testUseName = "userNameOne"
    val testPassWord = "passWordOne"
    val user = vm.createUser(testEmail, testUseName, testPassWord)

    assertEquals(user.email, testEmail)
    assertEquals(user.userName, testUseName)
    assertEquals(user.passWord, testPassWord)
  }

  @Test
  fun testNetworkCodeReason() {
    val userApi = mock(UserApi::class.java)
    val vm = RegisterVM(userApi)

    val nonHttpException = mock(Throwable::class.java)
    `when`(nonHttpException.localizedMessage).thenReturn("NonHttpError")
    assertEquals(vm.getReason(nonHttpException), "NonHttpError")

    val httpException = mock(HttpException::class.java)
    `when`(httpException.code()).thenReturn(409)
    assertEquals(vm.getReason(httpException), "This Username Already Exists")

    `when`(httpException.code()).thenReturn(452)
    assertEquals(vm.getReason(httpException), "Password Entered Is Too Weak")

    `when`(httpException.code()).thenReturn(503)
    assertEquals(vm.getReason(httpException), "Server Error Try Again Later")

    `when`(httpException.code()).thenReturn(0)
    assertEquals(vm.getReason(httpException), "Unidentified Error")
  }

}
