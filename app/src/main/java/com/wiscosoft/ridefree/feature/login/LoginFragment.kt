package com.wiscosoft.ridefree.feature.login

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.wiscosoft.ridefree.R
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.setThreads
import com.wiscosoft.ridefree.databinding.FragmentLoginBinding
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.domain.user.api.UserApi
import com.wiscosoft.ridefree.provider.redux.Action
import com.wiscosoft.ridefree.provider.redux.State
import com.wiscosoft.ridefree.provider.router.Router
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import redux.api.Store
import redux.asObservable
import retrofit2.HttpException

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

// VIEW MODEL
class LoginVM(private val userApi: UserApi, private val store: Store<State>) {

  fun userUpdate(): Flowable<User> = store.asObservable().toFlowable(BackpressureStrategy.LATEST).map { it.user }.setThreads()

  fun login(user: User?): Flowable<User> {
    return userApi.logon(user ?: store.state.user)
      .doOnNext {
        store.dispatch(Action.AuthUpdate(true))
        //store.dispatch(Action.UserUpdate(it))
      }
      .setThreads()
  }

  fun getReason(error: Throwable): String {
    if (error !is HttpException) return error.localizedMessage
    return when (error.code()) {
      414 -> "Too Many Clients"
      401 -> "Username / Password Incorrect"
      501 -> "Oops Something Went Wrong, Try Again Later"
      else -> "Unidentified Error: "
    }
  }
}

val loginModule = Kodein.Module {

  bind<LoginVM>() with singleton {
    val userApi: UserApi = instance()
    val store: Store<State> = instance()
    LoginVM(userApi, store)
  }

  bind<LoginFragment>() with singleton {
    LoginFragment()
  }

}