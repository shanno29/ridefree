package com.wiscosoft.ridefree.feature.fragment.account.register

import br.com.ilhasoft.support.validation.Validator
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.provider.repo.Repo
import dmax.dialog.SpotsDialog

class RegisterModule {

  val bind = Kodein.Module {
    bind("Register") from provider { user() }
    bind("Register") from provider { validator(instance()) }
    bind("Register") from provider { alertDialog(instance()) }
    bind() from provider { vm(instance(), instance("Register")) }
  }

  private fun user(): User = User()

  private fun validator(frag: RegisterFragment): Validator = Validator(frag.binding)

  private fun alertDialog(frag: RegisterFragment): SpotsDialog = SpotsDialog(frag.context, "Registering")

  private fun vm(repo: Repo, user: User): RegisterVM = RegisterVM(repo, user)

}