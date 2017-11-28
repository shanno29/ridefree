package com.wiscosoft.ridefree.feature.fragment.account.logout

import com.github.salomonbrys.kodein.instance
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.wiscosoft.ridefree.R.layout.fragment_logout
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.extensions.showMessage
import com.wiscosoft.ridefree.databinding.FragmentLogoutBinding

@Config(title = "Logout", layout = fragment_logout)
class LogoutFragment : BaseFragment<FragmentLogoutBinding>() {

  private val vm: LogoutVM by injector.instance()

  override fun onReady() {
    logoutUser()
  }

  private fun logoutUser() {
    vm.logout()
      .bindToLifecycle(provider)
      .subscribe({ activity.finishAffinity() }, this::prettyError)
  }

  private fun prettyError(throwable: Throwable) {
    showMessage(vm.getReason(throwable))
  }

}
