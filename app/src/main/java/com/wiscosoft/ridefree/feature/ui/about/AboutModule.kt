package com.wiscosoft.ridefree.feature.ui.about

import android.content.Context
import com.mikepenz.aboutlibraries.LibsBuilder
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IItem
import com.wiscosoft.ridefree.R
import com.wiscosoft.ridefree.core.app.Constants
import dagger.Module
import dagger.Provides

@Module
class AboutModule {

  @Provides
  fun aboutAdapter(context: Context): FastAdapter<IItem<*, *>> {
    return LibsBuilder()
      .withActivityTheme(R.style.RideFreeTheme)
      .withAboutDescription(Constants.about)
      .withAboutAppName(Constants.appName)
      .withVersionShown(true)
      .withLicenseShown(true)
      .withAutoDetect(true)
      .adapter(context)
  }

}