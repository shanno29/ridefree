package com.wiscosoft.ridefree.feature.about

import com.github.salomonbrys.kodein.instance
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IItem
import com.wiscosoft.ridefree.R.layout.fragment_about
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.databinding.FragmentAboutBinding

@Config(title = "About", layout = fragment_about)
class AboutFragment : BaseFragment<FragmentAboutBinding>() {

  private val adapter: FastAdapter<IItem<*, *>> by injector.instance()

  override fun onReady() {
    binding.recyclerView.adapter = adapter
  }

}
