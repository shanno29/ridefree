package com.wiscosoft.ridefree.feature.ui.account.login

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.wiscosoft.ridefree.core.test.BaseTest
import com.wiscosoft.ridefree.domain.user.UserApi
import com.wiscosoft.ridefree.provider.redux.State
import org.junit.Assert.assertEquals
import org.junit.Test
import redux.api.Store
import retrofit2.HttpException

class LoginVMTest : BaseTest() {

  @Test
  fun testNetworkCodeReason() {
    val store = mock<Store<State>>()
    val userApi = mock<UserApi>()
    val vm = LoginVM(userApi, store)

    val nonHttpException = mock<Throwable> { on { localizedMessage } doReturn "NonHttpError" }
    assertEquals(vm.reason(nonHttpException), "NonHttpError")

    val tooManyClients = mock<HttpException> { on { code() } doReturn 414 }
    assertEquals(vm.reason(tooManyClients), "Too Many Clients")

    val passwordEmailMismatch = mock<HttpException> { on { code() } doReturn 401 }
    assertEquals(vm.reason(passwordEmailMismatch), "Username / Password Incorrect")

    val serverIssue = mock<HttpException> { on { code() } doReturn 501 }
    assertEquals(vm.reason(serverIssue), "Server Error Try Again Later")

    val unidentified = mock<HttpException> { on { code() } doReturn 0 }
    assertEquals(vm.reason(unidentified), "Unidentified Error")

  }

}
