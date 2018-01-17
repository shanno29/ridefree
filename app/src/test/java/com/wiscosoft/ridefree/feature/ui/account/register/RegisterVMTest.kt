package com.wiscosoft.ridefree.feature.ui.account.register

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.wiscosoft.ridefree.core.test.BaseTest
import com.wiscosoft.ridefree.domain.user.UserApi
import com.wiscosoft.ridefree.provider.redux.State
import org.junit.Assert.assertEquals
import org.junit.Test
import redux.api.Store
import retrofit2.HttpException

class RegisterVMTest : BaseTest() {

  @Test
  fun testNetworkCodeReason() {
    val userApi = mock<UserApi>()
    val store = mock<Store<State>>()
    val vm = RegisterVM(userApi, store)

    val nonHttpException = mock<Throwable> { on { localizedMessage } doReturn "NonHttpError" }
    assertEquals(vm.reason(nonHttpException), "NonHttpError")

    val usernameExists = mock<HttpException> { on { code() } doReturn 409 }
    assertEquals(vm.reason(usernameExists), "This Username Already Exists")

    val weakPassword = mock<HttpException> { on { code() } doReturn 452 }
    assertEquals(vm.reason(weakPassword), "Password Entered Is Too Weak")

    val serverError = mock<HttpException> { on { code() } doReturn 503 }
    assertEquals(vm.reason(serverError), "Server Error Try Again Later")

    val unidentified = mock<HttpException> { on { code() } doReturn 0 }
    assertEquals(vm.reason(unidentified), "Unidentified Error")
  }

}

