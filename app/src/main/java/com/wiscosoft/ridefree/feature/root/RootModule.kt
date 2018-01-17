package com.wiscosoft.ridefree.feature.root

import com.wiscosoft.ridefree.feature.ui.about.AboutFragment
import com.wiscosoft.ridefree.feature.ui.about.AboutModule
import com.wiscosoft.ridefree.feature.ui.account.login.LoginFragment
import com.wiscosoft.ridefree.feature.ui.account.login.LoginModule
import com.wiscosoft.ridefree.feature.ui.account.logout.LogoutFragment
import com.wiscosoft.ridefree.feature.ui.account.logout.LogoutModule
import com.wiscosoft.ridefree.feature.ui.account.register.RegisterFragment
import com.wiscosoft.ridefree.feature.ui.account.register.RegisterModule
import com.wiscosoft.ridefree.feature.ui.map.MapFragment
import com.wiscosoft.ridefree.feature.ui.map.MapModule
import com.wiscosoft.ridefree.feature.ui.payments.PaymentAddFragment
import com.wiscosoft.ridefree.feature.ui.payments.PaymentInfoFragment
import com.wiscosoft.ridefree.feature.ui.payments.PaymentListFragment
import com.wiscosoft.ridefree.feature.ui.payments.PaymentsModule
import com.wiscosoft.ridefree.feature.ui.promos.PromosFragment
import com.wiscosoft.ridefree.feature.ui.promos.PromosModule
import com.wiscosoft.ridefree.feature.ui.rides.RideAddFragment
import com.wiscosoft.ridefree.feature.ui.rides.RideInfoFragment
import com.wiscosoft.ridefree.feature.ui.rides.RideListFragment
import com.wiscosoft.ridefree.feature.ui.rides.RidesModule
import com.wiscosoft.ridefree.feature.ui.settings.SettingsFragment
import com.wiscosoft.ridefree.feature.ui.settings.SettingsModule
import com.wiscosoft.ridefree.feature.ui.splash.SplashFragment
import com.wiscosoft.ridefree.feature.ui.splash.SplashModule
import com.wiscosoft.ridefree.feature.view.DrawerToggle
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class RootModule {

  @ContributesAndroidInjector(modules = [AboutModule::class])
  abstract fun aboutFragmentInjector(): AboutFragment

  @ContributesAndroidInjector(modules = [MapModule::class])
  abstract fun mapFragmentInjector(): MapFragment

  @ContributesAndroidInjector(modules = [PromosModule::class])
  abstract fun promosFragmentInjector(): PromosFragment

  @ContributesAndroidInjector(modules = [SettingsModule::class])
  abstract fun settingsFragmentInjector(): SettingsFragment

  @ContributesAndroidInjector(modules = [LoginModule::class])
  abstract fun loginFragmentInjector(): LoginFragment

  @ContributesAndroidInjector(modules = [LogoutModule::class])
  abstract fun logoutFragmentInjector(): LogoutFragment

  @ContributesAndroidInjector(modules = [RegisterModule::class])
  abstract fun registerFragmentInjector(): RegisterFragment

  @ContributesAndroidInjector(modules = [SplashModule::class])
  abstract fun splashFragmentInjector(): SplashFragment

  @ContributesAndroidInjector(modules = [PaymentsModule::class])
  abstract fun paymentInfoFragmentInjector(): PaymentInfoFragment

  @ContributesAndroidInjector(modules = [PaymentsModule::class])
  abstract fun paymentAddFragmentInjector(): PaymentAddFragment

  @ContributesAndroidInjector(modules = [PaymentsModule::class])
  abstract fun paymentListFragmentInjector(): PaymentListFragment

  @ContributesAndroidInjector(modules = [RidesModule::class])
  abstract fun rideInfoFragmentInjector(): RideInfoFragment

  @ContributesAndroidInjector(modules = [RidesModule::class])
  abstract fun rideAddFragmentInjector(): RideAddFragment

  @ContributesAndroidInjector(modules = [RidesModule::class])
  abstract fun rideListFragmentInjector(): RideListFragment

  @Module
  companion object {

    @Provides
    @JvmStatic
    fun toggle(rootActivity: RootActivity): DrawerToggle {
      return DrawerToggle(rootActivity)
    }

  }

}
