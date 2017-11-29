package com.wiscosoft.ridefree.feature.account.logout

import com.github.salomonbrys.kodein.instance
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.wiscosoft.ridefree.R.layout.fragment_logout
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.extensions.goTo
import com.wiscosoft.ridefree.core.extensions.showMessage
import com.wiscosoft.ridefree.databinding.FragmentLogoutBinding
import com.wiscosoft.ridefree.provider.router.Router

@Config(title = "Logout", layout = fragment_logout)
class LogoutFragment : BaseFragment<FragmentLogoutBinding>() {

  private val router: Router by injector.instance()
  private val vm: LogoutVM by injector.instance()

  override fun onReady() {
    logoutUser()
  }

  private fun logoutUser() {
    vm.logout().bindToLifecycle(provider)
      .map { router.splash() }
      .subscribe(this::goTo, this::prettyError)
  }

  private fun prettyError(throwable: Throwable) {
    showMessage(vm.getReason(throwable))
  }

}
