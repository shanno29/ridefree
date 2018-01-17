package com.wiscosoft.ridefree.provider.push

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class PushModule {

  @Provides
  @Singleton
  fun router(okHttpClient: OkHttpClient, gson: Gson): Push {
    return PushImpl(okHttpClient, gson)
  }

}
