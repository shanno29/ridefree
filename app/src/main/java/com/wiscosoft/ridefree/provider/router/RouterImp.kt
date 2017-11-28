package com.wiscosoft.ridefree.provider.router

import android.support.v4.app.Fragment
import com.wiscosoft.ridefree.feature.fragment.about.AboutFragment
import com.wiscosoft.ridefree.feature.fragment.account.login.LoginFragment
import com.wiscosoft.ridefree.feature.fragment.account.logout.LogoutFragment
import com.wiscosoft.ridefree.feature.fragment.account.register.RegisterFragment
import com.wiscosoft.ridefree.feature.fragment.map.MapFragment
import com.wiscosoft.ridefree.feature.fragment.payments.PaymentAddFragment
import com.wiscosoft.ridefree.feature.fragment.payments.PaymentInfoFragment
import com.wiscosoft.ridefree.feature.fragment.payments.PaymentListFragment
import com.wiscosoft.ridefree.feature.fragment.promos.PromosFragment
import com.wiscosoft.ridefree.feature.fragment.rides.RideAddFragment
import com.wiscosoft.ridefree.feature.fragment.rides.RideInfoFragment
import com.wiscosoft.ridefree.feature.fragment.rides.RidesListFragment
import com.wiscosoft.ridefree.feature.fragment.settings.SettingsFragment

class RouterImp : Router {


  override fun register(): Fragment = RegisterFragment()

  override fun login(): Fragment = LoginFragment()

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

  override fun fromTitle(name: String): Fragment = when (name) {
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
