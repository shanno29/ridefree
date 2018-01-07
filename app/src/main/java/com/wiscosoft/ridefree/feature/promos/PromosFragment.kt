package com.wiscosoft.ridefree.feature.promos

import com.github.salomonbrys.kodein.Kodein
import com.wiscosoft.ridefree.R
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.databinding.FragmentListBinding

@Config("Promos", R.layout.fragment_list)
class PromosFragment : BaseFragment<FragmentListBinding>() {

  override fun onReady() {
//    textView("Coming Soon")
//    textView("Check back in a future update!")
  }

}

val promosModule = Kodein.Module {}