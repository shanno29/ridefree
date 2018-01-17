package com.wiscosoft.ridefree.core.test

import com.wiscosoft.ridefree.R
import com.wiscosoft.ridefree.core.app.debugLog
import com.wiscosoft.ridefree.core.base.BaseActivity
import com.wiscosoft.ridefree.core.base.Layout
import com.wiscosoft.ridefree.databinding.ActivityTestBinding
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Layout(R.layout.activity_test)
class TestActivity : BaseActivity<ActivityTestBinding>() {

  override fun onReady() {
    super.onReady()
    debugLog("TestActivity Ready")
    /* Later Implementation */
  }

}

@Module
abstract class TestActivityModule {

  @ContributesAndroidInjector(modules = [TestFragmentModule::class])
  abstract fun testFragmentInjector(): TestFragment

}