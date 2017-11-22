package com.wiscosoft.ridefree.provider.prefs

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton

class PrefsModule {
  val bind = Kodein.Module {
    bind<SharedPreferences>() with singleton { defaultPrefs(instance()) }
    bind<RxSharedPreferences>() with singleton { rxPres(instance()) }
    bind<Prefs>() with singleton { prefs(instance()) }
  }

  private fun defaultPrefs(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

  private fun rxPres(sharedPreferences: SharedPreferences): RxSharedPreferences = RxSharedPreferences.create(sharedPreferences)

  private fun prefs(rxSharedPreferences: RxSharedPreferences): Prefs = PrefsImp(rxSharedPreferences)

}

