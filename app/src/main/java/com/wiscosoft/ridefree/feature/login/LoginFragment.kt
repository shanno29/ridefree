package com.wiscosoft.ridefree.feature.login

import com.github.salomonbrys.kodein.instance
import com.wiscosoft.ridefree.R
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.databinding.FragmentLoginBinding
import com.wiscosoft.ridefree.provider.router.Router

@Config("Login", R.layout.fragment_login)
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

  private val vm: LoginVM by injector.instance()
  private val router: Router by injector.instance()

  override fun onReady() {
    //setupUserUpdates()
    setupLoginButton()
    setupRegisterButton()
  }

//  fun userUpdates(): Disposable {
//    return vm.userUpdate()
//      .subscribe {
//        view.emailEt.setText(it.email)
//        view.usernameEt.setText(it.userName)
//        view.passwordEt.setText(it.passWord)
//      }
//  }

  private fun setupRegisterButton() {
//    sub.add(clicks(binding.btRegister)        // on register button clicked
//      .subscribe { goTo(router.register()) }  // go to register fragment
//    )
  }

  private fun setupLoginButton() {
//    sub.add(clicks(binding.btLogin)           // on login button clicked
//      .doOnEach { hideKeyboard() }            // hide keyboard
//      .filter { validator.validate() }        // on valid data
//      .subscribe { attempt() }                // attempt login
//    )
  }

  private fun attempt() {
//    sub.add(vm.login(binding.user)            // call network
//      .doOnSubscribe { loadingUI.show() }     // show loading
//      .doOnEach { loadingUI.dismiss() }       // hide loading
//      .subscribe(
//        { goTo(router.home()) },              // on success go to home fragment
//        { showMessage(vm.getReason(it)) }     // on error show formatted message
//      )
//    )
  }
}

