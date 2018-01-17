package com.wiscosoft.ridefree.feature.ui.splash

import com.google.android.gms.common.ConnectionResult.SUCCESS
import com.wiscosoft.ridefree.R.layout.fragment_splash
import com.wiscosoft.ridefree.core.app.showError
import com.wiscosoft.ridefree.databinding.FragmentSplashBinding
import com.wiscosoft.ridefree.feature.ui.account.login.LoginFragment
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Layout
import com.wiscosoft.ridefree.core.base.Title
import com.wiscosoft.ridefree.core.base.goTo
import javax.inject.Inject

@Title("Splash")
@Layout(fragment_splash)
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

  @Inject lateinit var vm: SplashVM

  override fun onReady() {
    super.onReady()
    checkPermissions()
  }

  fun checkPermissions() {
    sub.add(vm.checkPermissions()
      .subscribe(this::onResult, this::showError)
    )
  }

  fun requestPermissions() {
    sub.add(vm.requestPermissions()
      .subscribe(this::onResult, this::showError)
    )
  }

  fun onResult(ok: Boolean) {
    if (ok) checkGoogleApi() else requestPermissions()
  }

  fun checkGoogleApi() {
    sub.add(vm.checkGoogleApi()
      .subscribe {
        val res = it.isGooglePlayServicesAvailable(context)
        if (res == SUCCESS) goTo(LoginFragment())
        else it.getErrorDialog(activity, res, 0).show()
      }
    )
  }

}

