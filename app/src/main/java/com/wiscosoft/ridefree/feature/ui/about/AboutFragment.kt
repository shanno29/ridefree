package com.wiscosoft.ridefree.feature.ui.about

import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IItem
import com.wiscosoft.ridefree.R
import com.wiscosoft.ridefree.R.layout.fragment_list
import com.wiscosoft.ridefree.databinding.FragmentListBinding
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Layout
import com.wiscosoft.ridefree.core.base.Title
import javax.inject.Inject

@Title("About")
@Layout(R.layout.fragment_list)
class AboutFragment : BaseFragment<FragmentListBinding>() {

  @Inject lateinit var adapter: FastAdapter<IItem<*, *>>

  override fun onReady() {
    super.onReady()
    binding.recyclerView.adapter = adapter
  }

}