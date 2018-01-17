package com.wiscosoft.ridefree.feature.ui.account.login

import br.com.ilhasoft.support.validation.Validator
import com.jakewharton.rxbinding2.view.RxView.clicks
import com.wiscosoft.ridefree.R.layout.fragment_login
import com.wiscosoft.ridefree.core.app.hideKeyboard
import com.wiscosoft.ridefree.core.app.showMessage
import com.wiscosoft.ridefree.databinding.FragmentLoginBinding
import com.wiscosoft.ridefree.feature.ui.account.register.RegisterFragment
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Layout
import com.wiscosoft.ridefree.core.base.Title
import com.wiscosoft.ridefree.core.base.goTo
import javax.inject.Inject

@Title("Login")
@Layout(fragment_login)
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

  private val validator: Validator by lazy { Validator(binding) }
  @Inject lateinit var vm: LoginVM

  override fun onReady() {
    super.onReady()
    validator.enableFormValidationMode()
    setupRegisterButton()
    setupLoginButton()
  }

  private fun setupRegisterButton() {
    sub.add(clicks(binding.btRegister)
      .subscribe { goTo(RegisterFragment()) }
    )
  }

  private fun setupLoginButton() {
    sub.add(clicks(binding.btLogin)
      .doOnNext { hideKeyboard() }
      .filter { validator.validate() }
      .subscribe { attempt() }
    )
  }

  private fun attempt() {
    val username = binding.etUsername.text.toString()
    val password = binding.etPassword.text.toString()

    sub.add(vm.login(username, password)
      .doOnSubscribe { loadingUI.show() }
      .doOnEach { loadingUI.dismiss() }
      .subscribe(
        { goTo(LoginFragment()) },
        { showMessage(vm.reason(it)) }
      )
    )
  }

}