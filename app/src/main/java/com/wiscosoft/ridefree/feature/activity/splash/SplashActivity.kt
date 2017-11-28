package com.wiscosoft.ridefree.feature.activity.splash

import android.content.Intent
import com.github.salomonbrys.kodein.instance
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.wiscosoft.ridefree.R.layout.activity_splash
import com.wiscosoft.ridefree.core.base.BaseActivity
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.databinding.ActivitySplashBinding
import com.wiscosoft.ridefree.feature.activity.main.MainActivity

@Config(title = "Splash", layout = activity_splash)
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

  private val vm: SplashVM by injector.instance()

  override fun onReady() {
    checkPermissions()
  }

  private fun checkPermissions() {
    vm.checkPermissions()
      .bindToLifecycle(provider)
      .reduce(Boolean::and)
      .subscribe { if (!it) requestPermissions() else beginApp() }
  }

  private fun requestPermissions() {
    vm.requestPermissions()
      .bindToLifecycle(provider)
      .reduce(Boolean::and)
      .subscribe { checkPermissions() }
  }

  private fun beginApp() {
    startActivity(Intent(this, MainActivity::class.java))
  }

}
