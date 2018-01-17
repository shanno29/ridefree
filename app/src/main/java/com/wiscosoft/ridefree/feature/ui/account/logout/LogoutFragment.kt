package com.wiscosoft.ridefree.feature.ui.account.logout

import com.wiscosoft.ridefree.R.layout.fragment_logout
import com.wiscosoft.ridefree.core.app.showMessage
import com.wiscosoft.ridefree.databinding.FragmentLogoutBinding
import com.wiscosoft.ridefree.feature.ui.splash.SplashFragment
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Layout
import com.wiscosoft.ridefree.core.base.Title
import com.wiscosoft.ridefree.core.base.goTo
import javax.inject.Inject

@Title("Logout")
@Layout(fragment_logout)
class LogoutFragment : BaseFragment<FragmentLogoutBinding>() {

  @Inject lateinit var vm: LogoutVM

  override fun onReady() {
    super.onReady()
    logoutUser()
  }

  private fun logoutUser() {
    sub.add(vm.logout()
      .subscribe(
        { goTo(SplashFragment()) },
        { showMessage(vm.reason(it)) }
      )
    )
  }

}

