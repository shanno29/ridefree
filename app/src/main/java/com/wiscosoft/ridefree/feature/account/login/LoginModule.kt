package com.wiscosoft.ridefree.feature.account.login

import br.com.ilhasoft.support.validation.Validator
import com.github.salomonbrys.kodein.*
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.domain.user.UserApi
import com.wiscosoft.ridefree.provider.prefs.Prefs
import dmax.dialog.SpotsDialog

class LoginModule {

  val bind = Kodein.Module {
    bind<LoginFragment>() with singleton { loginFragment() }

    bind<User>("Login") with provider { user() }
    bind<Validator>("Login") with provider { validator(instance()) }
    bind<SpotsDialog>("Login") with provider { alertDialog(instance()) }
    bind<LoginVM>() with provider { vm(instance(), instance(), instance("Login")) }
  }

  private fun loginFragment() = LoginFragment()

  private fun user() = User()

  private fun validator(frag: LoginFragment) = Validator(frag.binding)

  private fun alertDialog(frag: LoginFragment) = SpotsDialog(frag.context, "Logging In")

  private fun vm(userApi: UserApi, prefs: Prefs, user: User) = LoginVM(userApi, prefs, user)

}