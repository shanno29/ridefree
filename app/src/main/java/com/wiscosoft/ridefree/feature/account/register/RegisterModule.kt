package com.wiscosoft.ridefree.feature.account.register

import br.com.ilhasoft.support.validation.Validator
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.domain.user.UserApi
import dmax.dialog.SpotsDialog

class RegisterModule {

  val bind = Kodein.Module {
    bind<User>("Register") with provider { user() }
    bind<Validator>("Register") with provider { validator(instance()) }
    bind<SpotsDialog>("Register") with provider { alertDialog(instance()) }
    bind<RegisterVM>() with provider { vm(instance(), instance("Register")) }
  }

  private fun user(): User = User()

  private fun validator(frag: RegisterFragment): Validator = Validator(frag.binding)

  private fun alertDialog(frag: RegisterFragment): SpotsDialog = SpotsDialog(frag.context, "Registering")

  private fun vm(userApi: UserApi, user: User): RegisterVM = RegisterVM(userApi, user)

}