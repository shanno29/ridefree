package com.wiscosoft.ridefree.provider.router

import android.support.v4.app.Fragment
import com.wiscosoft.ridefree.feature.about.AboutFragment
import com.wiscosoft.ridefree.feature.account.login.LoginFragment
import com.wiscosoft.ridefree.feature.account.logout.LogoutFragment
import com.wiscosoft.ridefree.feature.account.register.RegisterFragment
import com.wiscosoft.ridefree.feature.map.MapFragment
import com.wiscosoft.ridefree.feature.payments.PaymentAddFragment
import com.wiscosoft.ridefree.feature.payments.PaymentInfoFragment
import com.wiscosoft.ridefree.feature.payments.PaymentListFragment
import com.wiscosoft.ridefree.feature.promos.PromosFragment
import com.wiscosoft.ridefree.feature.rides.RideAddFragment
import com.wiscosoft.ridefree.feature.rides.RideInfoFragment
import com.wiscosoft.ridefree.feature.rides.RidesListFragment
import com.wiscosoft.ridefree.feature.settings.SettingsFragment
import com.wiscosoft.ridefree.feature.splash.SplashFragment

class RouterImp
  constructor(private val loginFragment: LoginFragment): Router {

  override fun splash(): Fragment = SplashFragment()

  override fun register(): Fragment = RegisterFragment()

  override fun login(): Fragment = loginFragment

  override fun logout(): Fragment = LogoutFragment()

  override fun about(): Fragment = AboutFragment()

  override fun map(): Fragment = MapFragment()

  override fun promos(): Fragment = PromosFragment()

  override fun settings(): Fragment = SettingsFragment()

  override fun paymentAdd(): Fragment = PaymentAddFragment()
  override fun paymentList(): Fragment = PaymentListFragment()
  override fun paymentInfo(id: Int): Fragment = PaymentInfoFragment().also { it.id = id }

  override fun rideAdd(): Fragment = RideAddFragment()
  override fun rideList(): Fragment = RidesListFragment()
  override fun rideInfo(id: Int): Fragment = RideInfoFragment().also { it.id = id }

  override fun fromTitle(name: CharSequence): Fragment = when (name) {
    "Home" -> map()
    "Rides" -> rideList()
    "Promos" -> promos()
    "Payment" -> paymentList()
    "Settings" -> settings()
    "Logout" -> logout()
    "About" -> about()
    else -> Fragment()
  }

}
