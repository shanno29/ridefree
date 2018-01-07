package com.wiscosoft.ridefree.provider.prefs

import com.f2prateek.rx.preferences2.Preference
import io.reactivex.Flowable

interface Prefs {

  val cookies: Preference<Set<String>>

  val cookiesRx: Flowable<Set<String>>

  fun clearAll()
}