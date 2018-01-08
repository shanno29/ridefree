package com.wiscosoft.ridefree.feature.register

import com.github.salomonbrys.kodein.instance
import com.jakewharton.rxbinding2.view.RxView.clicks
import com.wiscosoft.ridefree.R
import com.wiscosoft.ridefree.core.*
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.databinding.FragmentRegisterBinding
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.provider.router.Router

@Config("Register", R.layout.fragment_register)
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

  private val vm: RegisterVM by injector.instance()
  private val router: Router by injector.instance()

  override fun onReady() {
    registerBtnUpdates()
    loginBtnUpdates()
  }

  fun loginBtnUpdates() {
    sub.add(clicks(binding.btLogin)
      .subscribe { goTo(router.login) }
    )
  }

  fun registerBtnUpdates() {
    sub.add(clicks(binding.btRegister)
      .doOnEach { hideKeyboard() }
      .map { userFromView() }
      .filter(this::validateInput)
      .subscribe(this::attemptRegister)
    )
  }

  fun userFromView(): User {
    return vm.createUser(
      binding.etEmailLayout.value,
      binding.etUsernameLayout.value,
      binding.etPasswordOneLayout.value
    )
  }

  fun validateInput(user: User): Boolean {
    var err = 0
    if (user.email.isBlank() || !user.email.contains("@")) err++; binding.etEmailLayout.error = "Invalid Email Address"
    if (user.userName.isBlank()) err++; binding.etUsernameLayout.error = "Invalid or Short Username"
    if (user.passWord.isBlank()) err++; binding.etPasswordLayout.error = "Invalid or Weak Password"
    return err == 0
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



