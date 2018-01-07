package com.wiscosoft.ridefree.feature.register

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.wiscosoft.ridefree.R
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.goTo
import com.wiscosoft.ridefree.core.setThreads
import com.wiscosoft.ridefree.core.showMessage
import com.wiscosoft.ridefree.databinding.FragmentLoginBinding
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.domain.user.api.UserApi
import com.wiscosoft.ridefree.provider.router.Router
import io.reactivex.Flowable
import retrofit2.HttpException

@Config("Register", R.layout.fragment_register)
class RegisterFragment : BaseFragment<FragmentLoginBinding>() {

  private val vm: RegisterVM by injector.instance()
  private val router: Router by injector.instance()

  override fun onReady() {
    loginBtnUpdates()
    registerBtnUpdates()
  }

  fun loginBtnUpdates() {
//    sub.add(clicks(binding?.btLogin)
//      .subscribe { goTo(router.login) }
//    )
  }

  fun registerBtnUpdates() {
//    sub.add(clicks(binding?.btRegister)
//      .doOnEach { hideKeyboard() }
//      .subscribe { validateInput() }
//    )
  }

  fun validateInput() {
//    val user = vm.createUser(
//      view.emailLayout.value,
//      view.usernameLayout.value,
//      view.passwordLayout.value
//    )
//
//    var err = 0
//    if (user.email.isBlank() || !user.email.contains("@")) err++; view.emailLayout.error = "Invalid Email Address"
//    if (user.userName.isBlank()) err++; view.usernameLayout.error = "Invalid or Short Username"
//    if (user.passWord.isBlank()) err++; view.passwordLayout.error = "Invalid or Weak Password"
//    if (err == 0) attemptRegister(user)
  }

  fun attemptRegister(user: User) {
    sub.add(vm.register(user)
      .doOnSubscribe { loadingUI.show() }
      .doOnEach { loadingUI.hide() }
      .setThreads()
      .subscribe(
        { goTo(router.login) },
        { showMessage(vm.getReason(it)) }
      )
    )
  }
}

class RegisterVM(private val userApi: UserApi) {

  fun register(user: User): Flowable<User> = userApi.logon(user).setThreads()

  fun createUser(email: String, username: String, password: String): User {
    return User.default.copy(
      email = email,
      userName = username,
      passWord = password
    )
  }

  fun getReason(error: Throwable): String {
    if (error !is HttpException) return error.localizedMessage
    return when (error.code()) {
      409 -> "This Username Already Exists"
      452 -> "Password Entered Is Too Weak"
      503 -> "Server Error Try Again Later"
      else -> "Unidentified Error: "
    }
  }

}

val registerModule = Kodein.Module {

  bind<RegisterVM>() with singleton {
    val userApi: UserApi = instance()
    RegisterVM(userApi)
  }

  bind<RegisterFragment>() with singleton {
    RegisterFragment()
  }

}
