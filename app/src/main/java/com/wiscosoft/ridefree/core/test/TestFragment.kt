package com.wiscosoft.ridefree.core.test

import com.wiscosoft.ridefree.R.layout.fragment_test
import com.wiscosoft.ridefree.core.app.debugLog
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Layout
import com.wiscosoft.ridefree.core.base.Title
import com.wiscosoft.ridefree.databinding.FragmentTestBinding
import dagger.Module

@Title("TestTitle")
@Layout(fragment_test)
class TestFragment : BaseFragment<FragmentTestBinding>() {

  override fun onReady() {
    super.onReady()
    debugLog("TestFragment Ready")
    /* Later Implementation */
  }

}

@Module
abstract class TestFragmentModule {

  @Module
  companion object {

  }

}
