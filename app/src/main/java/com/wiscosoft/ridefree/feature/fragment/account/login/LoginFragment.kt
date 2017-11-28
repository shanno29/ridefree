package com.wiscosoft.ridefree.feature.fragment.account.login

import br.com.ilhasoft.support.validation.Validator
import com.github.salomonbrys.kodein.Kodein.Module
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.jakewharton.rxbinding2.view.RxView.clicks
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.wiscosoft.ridefree.R.layout.fragment_login
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.extensions.goTo
import com.wiscosoft.ridefree.core.extensions.hideKeyboard
import com.wiscosoft.ridefree.core.extensions.showMessage
import com.wiscosoft.ridefree.databinding.FragmentLoginBinding
import com.wiscosoft.ridefree.provider.router.Router
import dmax.dialog.SpotsDialog

@Config(title = "Login", layout = fragment_login)
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

  override fun provideOverridingModule() = Module { bind() from instance(this@LoginFragment) }
  private val validator: Validator by injector.instance("Login")
  private val dialog: SpotsDialog by injector.instance("Login")
  private val router: Router by injector.instance()
  private val vm: LoginVM by injector.instance()

  override fun onReady() {
    validator.enableFormValidationMode()
    setupRegisterButton()
    setupLoginButton()
    binding.vm = vm
  }

  private fun setupRegisterButton() {
    clicks(binding.btRegister)
      .bindToLifecycle(provider)
      .map { router.register() }
      .subscribe(this::goTo)
  }

  private fun setupLoginButton() {
    clicks(binding.btLogin)
      .bindToLifecycle(provider)
      .doOnNext { hideKeyboard() }
      .filter { validator.validate() }
      .subscribe { attempt() }
  }

  private fun attempt() {
    vm.login()
      .bindToLifecycle(provider)
      .doOnSubscribe { dialog.show() }
      .doOnEach { dialog.dismiss() }
      .map { router.map() }
      .subscribe(this::goTo, this::prettyError)
  }

  private fun prettyError(throwable: Throwable) {
    showMessage(vm.getReason(throwable))
  }

}

