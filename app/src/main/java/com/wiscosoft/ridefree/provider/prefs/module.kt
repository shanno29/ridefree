package com.wiscosoft.ridefree.provider.prefs

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton

val prefsModule = Kodein.Module {

  bind<SharedPreferences>() with singleton {
    val context: Context = instance()
    PreferenceManager.getDefaultSharedPreferences(context)
  }

  bind<RxSharedPreferences>() with singleton {
    val sharedPrefs: SharedPreferences = instance()
    RxSharedPreferences.create(sharedPrefs).apply { }
  }

  bind<Prefs>() with singleton {
    val rxPrefs: RxSharedPreferences = instance()
    PrefsImpl(rxPrefs)
  }

}

