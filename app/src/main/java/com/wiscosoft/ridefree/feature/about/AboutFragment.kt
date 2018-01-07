package com.wiscosoft.ridefree.feature.about

import android.content.Context
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.mikepenz.aboutlibraries.LibsBuilder
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IItem
import com.wiscosoft.ridefree.R
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.databinding.FragmentListBinding

@Config("About", R.layout.fragment_about)
class AboutFragment : BaseFragment<FragmentListBinding>() {

  private val adapter: AboutAdapter by injector.instance()

  override fun onReady() {
    binding.recyclerView.adapter = adapter
  }

}

typealias AboutAdapter = FastAdapter<IItem<*, *>>

val aboutModule = Kodein.Module {
  bind<AboutAdapter>() with singleton {
    val context: Context = instance()
    LibsBuilder()
      .withAboutDescription("Created By Matthew Shannon")
      .withAboutAppName("RideFree 2018")
      .withActivityTheme(R.style.RideFreeTheme)
      .withVersionShown(true)
      .withLicenseShown(true)
      .withAutoDetect(true)
      .adapter(context)
  }

  bind<AboutFragment>() with singleton {
    AboutFragment()
  }
}