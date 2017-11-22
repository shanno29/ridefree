package com.wiscosoft.ridefree.feature.fragment.account.login

import br.com.ilhasoft.support.validation.Validator
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.provider.prefs.Prefs
import com.wiscosoft.ridefree.provider.repo.Repo
import dmax.dialog.SpotsDialog

class LoginModule {

  val bind = Kodein.Module {
    bind("Login") from provider { user() }
    bind("Login") from provider { validator(instance()) }
    bind("Login") from provider { alertDialog(instance()) }
    bind() from provider { vm(instance(), instance(), instance("Login")) }
  }

  private fun user(): User = User()

  private fun validator(frag: LoginFragment): Validator = Validator(frag.binding)

  private fun alertDialog(frag: LoginFragment): SpotsDialog = SpotsDialog(frag.context, "Logging In")

  private fun vm(repo: Repo, prefs: Prefs, user: User): LoginVM = LoginVM(repo, prefs, user)

}