package com.wiscosoft.ridefree.feature.splash

import com.github.salomonbrys.kodein.instance
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.wiscosoft.ridefree.R.layout.fragment_splash
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.extensions.goTo
import com.wiscosoft.ridefree.databinding.FragmentSplashBinding
import com.wiscosoft.ridefree.provider.router.Router


@Config(title = "RideFree", layout = fragment_splash)
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

  private val router: Router by injector.instance()
  private val vm: SplashVM by injector.instance()

  override fun onReady() {
    checkPermissions()
  }

  private fun checkPermissions() {
    vm.checkPermissions().bindToLifecycle(provider)
      .subscribe { if (!it) requestPermissions() else goTo(router.login()) }
  }

  private fun requestPermissions() {
    vm.requestPermissions().bindToLifecycle(provider)
      .subscribe { checkPermissions() }
  }

}
