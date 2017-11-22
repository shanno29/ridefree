package com.wiscosoft.ridefree.provider.router

import android.support.v4.app.Fragment
import com.wiscosoft.ridefree.feature.fragment.about.AboutFragment
import com.wiscosoft.ridefree.feature.fragment.account.login.LoginFragment
import com.wiscosoft.ridefree.feature.fragment.account.logout.LogoutFragment
import com.wiscosoft.ridefree.feature.fragment.account.register.RegisterFragment
import com.wiscosoft.ridefree.feature.fragment.history.detail.HistoryDetailFragment
import com.wiscosoft.ridefree.feature.fragment.history.list.HistoryListFragment
import com.wiscosoft.ridefree.feature.fragment.map.MapFragment
import com.wiscosoft.ridefree.feature.fragment.payments.add.PaymentAddFragment
import com.wiscosoft.ridefree.feature.fragment.payments.list.PaymentsListFragment
import com.wiscosoft.ridefree.feature.fragment.promos.PromosFragment
import com.wiscosoft.ridefree.feature.fragment.settings.SettingsFragment

class RouterImp : Router {

  override fun register(): Fragment = RegisterFragment()

  override fun login(): Fragment = LoginFragment()

  override fun logout(): Fragment = LogoutFragment()

  override fun about(): Fragment = AboutFragment()

  override fun map(): Fragment = MapFragment()

  override fun paymentAdd(): Fragment = PaymentAddFragment()

  override fun payment(): Fragment = PaymentsListFragment()

  override fun promos(): Fragment = PromosFragment()

  override fun settings(): Fragment = SettingsFragment()

  override fun history(): Fragment = HistoryListFragment()

  override fun historyDetail(id: Int): Fragment = HistoryDetailFragment().also { it.rideId = id }

  override fun fromTitle(name: String): Fragment = when (name) {
    "Login" -> login()
    "Register" -> register()
    "About" -> about()
    "History" -> history()
    "Home" -> map()
    "Logout" -> logout()
    "Promos" -> promos()
    "Settings" -> settings()
    "Payment" -> payment()
    "Payment Add" -> paymentAdd()
    else -> Fragment()
  }

}
