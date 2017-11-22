package com.wiscosoft.ridefree.feature.fragment.account.login

import br.com.ilhasoft.support.validation.Validator
import com.github.salomonbrys.kodein.Kodein.Module
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.jakewharton.rxbinding2.view.RxView.clicks
import com.wiscosoft.ridefree.R.layout.fragment_login
import com.wiscosoft.ridefree.core.base.*
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
  private val vm: LoginVM by injector.instance()
  private val router: Router by injector.instance()

  override fun onReady() {
    validator.enableFormValidationMode()
    binding.vm = vm
    setupRegisterButton()
    setupLoginButton()
  }

  private fun setupRegisterButton() {
    sub.add(clicks(binding.btRegister)
        .subscribe { goTo(router.register()) })
  }

  private fun setupLoginButton() {
    sub.add(clicks(binding.btLogin)
        .doOnNext { hideKeyboard() }
        .filter { validator.validate() }
        .subscribe { attempt() }
    )
  }

  private fun attempt() {
    sub.add(vm.login()
        .doOnSubscribe { dialog.show() }
        .doOnEach { dialog.dismiss() }
        .subscribe(
            { _ -> goTo(router.map()) },
            { err -> showMessage(vm.getReason(err)) }
        )
    )
  }

}