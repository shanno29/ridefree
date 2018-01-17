package com.wiscosoft.ridefree.core.app

import android.content.Context
import com.wiscosoft.ridefree.core.test.TestActivity
import com.wiscosoft.ridefree.core.test.TestActivityModule
import com.wiscosoft.ridefree.core.test.TestService
import com.wiscosoft.ridefree.core.test.TestServiceModule
import com.wiscosoft.ridefree.domain.DomainModule
import com.wiscosoft.ridefree.feature.notifications.NotificationModule
import com.wiscosoft.ridefree.feature.notifications.NotificationService
import com.wiscosoft.ridefree.feature.root.RootActivity
import com.wiscosoft.ridefree.feature.root.RootModule
import com.wiscosoft.ridefree.provider.ProviderModule
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
  AndroidSupportInjectionModule::class,
  AppComponent.AppModule::class,

  ProviderModule::class,
  DomainModule::class
])
interface AppComponent : AndroidInjector<App> {

  @Component.Builder
  abstract class Builder : AndroidInjector.Builder<App>()

  @Module
  abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindContext(app: App): Context

    @ContributesAndroidInjector(modules = [RootModule::class])
    abstract fun rootActivityInjector(): RootActivity

    @ContributesAndroidInjector(modules = [TestActivityModule::class])
    abstract fun testActivityInjector(): TestActivity

    @ContributesAndroidInjector(modules = [NotificationModule::class])
    abstract fun notificationServiceInjector(): NotificationService

    @ContributesAndroidInjector(modules = [TestServiceModule::class])
    abstract fun testServiceInjector(): TestService

  }

}
