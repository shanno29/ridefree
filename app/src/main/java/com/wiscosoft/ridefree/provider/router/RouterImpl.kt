package com.wiscosoft.ridefree.provider.router

import android.support.v4.app.Fragment
import com.wiscosoft.ridefree.feature.about.AboutFragment
import com.wiscosoft.ridefree.feature.login.LoginFragment
import com.wiscosoft.ridefree.feature.logout.LogoutFragment
import com.wiscosoft.ridefree.feature.map.MapFragment
import com.wiscosoft.ridefree.feature.promos.PromosFragment
import com.wiscosoft.ridefree.feature.register.RegisterFragment
import com.wiscosoft.ridefree.feature.settings.SettingsFragment
import com.wiscosoft.ridefree.feature.splash.SplashFragment

class RouterImpl : Router {

  override val splash: Fragment = SplashFragment()

  override val register: Fragment = RegisterFragment()

  override val login: Fragment = LoginFragment()

  override val logout: Fragment = LogoutFragment()

  override val about: Fragment = AboutFragment()

  override val home: Fragment = MapFragment()

  override val promos: Fragment = PromosFragment()

  override val settings: Fragment = SettingsFragment()

//  override fun paymentAdd(): Fragment = PaymentAddFragment()
//  override fun paymentList(): Fragment = PaymentListFragment()
//  override fun paymentInfo(id: Int): Fragment = PaymentInfoFragment().also { it.id = id }

//  override fun rideAdd(): Fragment = RideAddFragment()
//  override fun rideList(): Fragment = RidesListFragment()
//  override fun rideInfo(id: Int): Fragment = RideInfoFragment().also { it.id = id }

  override fun fromTitle(name: CharSequence): Fragment = when (name) {
    "Home" -> home
  //  "Rides" -> rideList()
    "Promos" -> promos
  //  "Payment" -> paymentList()
    "Settings" -> settings
    "Logout" -> logout
    "About" -> about
    else -> Fragment()
  }
}