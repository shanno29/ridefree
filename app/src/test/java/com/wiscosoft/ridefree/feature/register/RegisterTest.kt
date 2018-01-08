package com.wiscosoft.ridefree.feature.register

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.wiscosoft.ridefree.provider.api.entity.user.UserApi
import org.junit.Assert.*
import org.junit.Test
import retrofit2.HttpException

class RegisterTest {

  @Test
  fun testVM()  {
    val userApi = mock<UserApi>()

    val vm = RegisterVM(userApi)

    assertNotNull(vm)
  }

  @Test
  fun testCreateUser() {
    val userApi = mock<UserApi>()
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
    val userApi = mock<UserApi>()
    val vm = RegisterVM(userApi)

    val nonHttpException: Throwable = mock { on { localizedMessage } doReturn "NonHttpError" }
    assertEquals(vm.getReason(nonHttpException), "NonHttpError")

    val usernameException: HttpException = mock { on { code() } doReturn(409) }
    assertEquals(vm.getReason(usernameException), "This Username Already Exists")

    val passwordException: HttpException = mock { on { code() } doReturn(452) }
    assertEquals(vm.getReason(passwordException), "Password Entered Is Too Weak")

    val serverException: HttpException = mock { on { code() } doReturn(503) }
    assertEquals(vm.getReason(serverException), "Server Error Try Again Later")

    val emptyException: HttpException = mock { on { code() } doReturn(0) }
    assertEquals(vm.getReason(emptyException), "Unidentified Error")
  }

}
