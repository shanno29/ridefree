package com.wiscosoft.ridefree.provider.push

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.google.gson.Gson
import okhttp3.OkHttpClient

val pushModule = Kodein.Module {

  bind<Push>() with singleton {
    val okHttpClient: OkHttpClient = instance()
    val gson: Gson = instance()
    PushImp(okHttpClient, gson)
  }
}