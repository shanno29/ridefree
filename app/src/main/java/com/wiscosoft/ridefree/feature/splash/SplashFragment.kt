package com.wiscosoft.ridefree.feature.splash

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CAMERA
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import com.apt7.rxpermissions.Permission.PERMISSION_GRANTED
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.google.android.gms.common.ConnectionResult.SUCCESS
import com.google.android.gms.common.GoogleApiAvailability
import com.wiscosoft.ridefree.R
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.goTo
import com.wiscosoft.ridefree.core.setThreads
import com.wiscosoft.ridefree.core.showError
import com.wiscosoft.ridefree.databinding.FragmentSplashBinding
import com.wiscosoft.ridefree.provider.gps.Gps
import com.wiscosoft.ridefree.provider.permissions.Permissions
import com.wiscosoft.ridefree.provider.router.Router
import io.reactivex.Flowable
import io.reactivex.Single

@Config("Splash", R.layout.fragment_splash)
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

  private val router: Router by injector.instance()
  private val vm: SplashVM by injector.instance()

  override fun onReady() {
    checkPermissions()
  }

  private fun checkPermissions() {
    sub.add(vm.checkPermissions
      .subscribe(this::onResult, this::showError)
    )
  }

  private fun requestPermissions() {
    sub.add(vm.requestPermissions
      .subscribe(this::onResult, this::showError)
    )
  }

  private fun onResult(ok: Boolean) {
    if (!ok) requestPermissions() else checkGoogleApi()
  }

  private fun checkGoogleApi() {
    sub.add(vm.checkGoogleApi
      .subscribe {
        val status = it.isGooglePlayServicesAvailable(context)
        if (status == SUCCESS) goTo(router.login)
        else it.getErrorDialog(activity, status, 0).show()
      }
    )
  }
}

class SplashVM(permissions: Permissions, gps: Gps) {

  val checkPermissions: Single<Boolean> = permissions
    .check(WRITE_EXTERNAL_STORAGE, ACCESS_FINE_LOCATION, CAMERA)
    .map { it.granted == PERMISSION_GRANTED }
    .reduce(Boolean::and).toSingle()
    .setThreads()

  val requestPermissions: Single<Boolean> = permissions
    .request(WRITE_EXTERNAL_STORAGE, ACCESS_FINE_LOCATION, CAMERA)
    .map { it.granted == PERMISSION_GRANTED }
    .reduce(Boolean::and).toSingle()
    .setThreads()

  val checkGoogleApi: Flowable<GoogleApiAvailability> = gps
    .googleApi
    .setThreads()
}

val splashModule = Kodein.Module {

  bind<SplashVM>() with singleton {
    val perms: Permissions = instance()
    val gps: Gps = instance()
    SplashVM(perms, gps)
  }

}