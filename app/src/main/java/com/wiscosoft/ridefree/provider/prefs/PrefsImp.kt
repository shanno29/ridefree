package com.wiscosoft.ridefree.provider.prefs

import com.f2prateek.rx.preferences2.Preference
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.wiscosoft.ridefree.core.flowable
import io.reactivex.Flowable

class PrefsImp(private val rxPrefs: RxSharedPreferences) : Prefs {

  override val cookies: Preference<Set<String>> = rxPrefs.getStringSet("Cookies", emptySet())

  override val cookiesRx: Flowable<Set<String>> = cookies.asObservable().flowable

  override fun clearAll() = rxPrefs.clear()
}