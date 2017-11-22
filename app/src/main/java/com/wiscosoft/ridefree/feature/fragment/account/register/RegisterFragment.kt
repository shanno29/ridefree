package com.wiscosoft.ridefree.feature.fragment.account.register

import br.com.ilhasoft.support.validation.Validator
import com.github.salomonbrys.kodein.Kodein.Module
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.jakewharton.rxbinding2.view.RxView.clicks
import com.wiscosoft.ridefree.R.layout.fragment_register
import com.wiscosoft.ridefree.core.base.*
import com.wiscosoft.ridefree.core.extensions.goTo
import com.wiscosoft.ridefree.core.extensions.hideKeyboard
import com.wiscosoft.ridefree.core.extensions.showMessage
import com.wiscosoft.ridefree.databinding.FragmentRegisterBinding
import com.wiscosoft.ridefree.provider.router.Router
import dmax.dialog.SpotsDialog

@Config(title = "Register", layout = fragment_register)
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

  override fun provideOverridingModule() = Module { bind() from instance(this@RegisterFragment) }
  private val validator: Validator by injector.instance("Register")
  private val dialog: SpotsDialog by injector.instance("Register")
  private val vm: RegisterVM by injector.instance()
  private val router: Router by injector.instance()

  override fun onReady() {
    validator.enableFormValidationMode()
    binding.vm = vm
    setupRegisterButton()
    setupLoginButton()
  }

  private fun setupRegisterButton() {
    sub.add(clicks(binding.btRegister)
        .doOnNext { hideKeyboard() }
        .filter { validator.validate() }
        .subscribe { attempt() }
    )
  }

  private fun setupLoginButton() {
    sub.add(clicks(binding.btLogin)
        .subscribe { _ -> goTo(router.login()) }
    )
  }

  private fun attempt() {
    sub.add(vm.register()
        .doOnSubscribe { dialog.show() }
        .doOnEach { dialog.dismiss() }
        .subscribe(
            { goTo(router.login()) },
            { showMessage(vm.getReason(it)) })
    )
  }

}
