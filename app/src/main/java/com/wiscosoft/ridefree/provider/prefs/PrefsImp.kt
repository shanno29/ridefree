package com.wiscosoft.ridefree.provider.prefs

import com.f2prateek.rx.preferences2.Preference
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.wiscosoft.ridefree.core.extensions.log
import io.reactivex.BackpressureStrategy.LATEST
import io.reactivex.Flowable
import java.util.Collections.emptySet

class PrefsImp
constructor(private val rxPrefs: RxSharedPreferences) : Prefs {

  override fun owner(): Preference<Int> = rxPrefs.getInteger("owner", -1)

  override fun ownerRx(): Flowable<Int> = owner().asObservable().toFlowable(LATEST).compose(log())


  override fun firstRun(): Preference<Boolean> = rxPrefs.getBoolean("firstRun", true)

  override fun firstRunRx(): Flowable<Boolean> = firstRun().asObservable().toFlowable(LATEST).compose(log())


  override fun cookies(): Preference<Set<String>> = rxPrefs.getStringSet("cookies", emptySet())

  override fun cookiesRx(): Flowable<Set<String>> = cookies().asObservable().toFlowable(LATEST).compose(log())


  override fun clearAll() = rxPrefs.clear()

}
