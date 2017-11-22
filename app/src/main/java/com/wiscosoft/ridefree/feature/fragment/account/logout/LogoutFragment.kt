package com.wiscosoft.ridefree.feature.fragment.account.logout

import com.github.salomonbrys.kodein.instance
import com.wiscosoft.ridefree.R.layout.fragment_logout
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.extensions.showMessage
import com.wiscosoft.ridefree.databinding.FragmentLogoutBinding

@Config(title = "Logout", layout = fragment_logout)
class LogoutFragment : BaseFragment<FragmentLogoutBinding>() {

  private val vm: LogoutVM by injector.instance()

  override fun onReady() {
    attempt()
  }

  private fun attempt() {
    sub.add(vm.logout()
        .subscribe(
            { _ -> activity.finishAffinity() },
            { err -> showMessage(vm.getReason(err)) }
        )
    )
  }


}