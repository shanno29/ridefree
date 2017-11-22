package com.wiscosoft.ridefree.feature.activity.splash

import android.content.Intent
import com.github.salomonbrys.kodein.instance
import com.wiscosoft.ridefree.R.layout.activity_splash
import com.wiscosoft.ridefree.feature.activity.main.MainActivity
import com.wiscosoft.ridefree.core.base.BaseActivity
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.databinding.ActivitySplashBinding

@Config(title = "Splash", layout = activity_splash)
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

  private val vm: SplashVM by injector.instance()

  override fun onReady() {
    checkPermissions()
  }

  private fun checkPermissions() {
    sub.add(vm.checkPermissions()
        .reduce { x, y -> x && y }
        .subscribe { if (!it) requestPermissions() else beginApp() }
    )
  }

  private fun requestPermissions() {
    sub.add(vm.requestPermissions()
        .reduce { x, y -> x && y }
        .subscribe { checkPermissions() }
    )
  }

  private fun beginApp() {
    startActivity(Intent(this, MainActivity::class.java))
  }

}
