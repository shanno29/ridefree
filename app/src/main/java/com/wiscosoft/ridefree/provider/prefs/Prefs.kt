package com.wiscosoft.ridefree.provider.prefs

import com.f2prateek.rx.preferences2.Preference
import io.reactivex.Flowable

interface Prefs {


  fun owner(): Preference<Int>

  fun ownerRx(): Flowable<Int>


  fun firstRun(): Preference<Boolean>

  fun firstRunRx(): Flowable<Boolean>


  fun cookies(): Preference<Set<String>>

  fun cookiesRx(): Flowable<Set<String>>


  fun clearAll()

}
