package com.wiscosoft.ridefree.provider.network

import android.content.Context
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.google.gson.Gson
import com.wiscosoft.ridefree.provider.prefs.Prefs
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModule {

  val bind = Kodein.Module {
    bind() from singleton { rxFactory() }
    bind() from singleton { gson() }
    bind() from singleton { gsonFactory(instance()) }
    bind() from singleton { cache(instance()) }
    bind("log") from singleton { loggingInterceptor() }
    bind("req") from singleton { requestWithAuth(instance()) }
    bind("res") from singleton { responseWithAuth(instance()) }
    bind() from singleton { okHttpClient(instance(), instance("log"), instance("req"), instance("res")) }
    bind() from singleton { retrofit(instance("api"), instance(), instance(), instance()) } //TODO
  }

  private fun gson(): Gson = Gson()

  private fun gsonFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

  private fun rxFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

  private fun cache(context: Context): Cache = Cache(context.cacheDir, (10 * 1024 * 1024).toLong())

  private fun loggingInterceptor(): Interceptor {
    return HttpLoggingInterceptor()
      .setLevel(BODY)
  }

  private fun requestWithAuth(prefs: Prefs) = Interceptor {
    val builder = it.request().newBuilder()
    prefs.cookies().get().forEach { builder.addHeader("Cookie", it) }
    it.proceed(builder.build())
  }

  private fun responseWithAuth(prefs: Prefs) = Interceptor {
    val res = it.proceed(it.request())
    prefs.cookies().set(HashSet(res.headers("Set-Cookie")))
    res
  }

  private fun okHttpClient(cache: Cache, logger: Interceptor, req: Interceptor, res: Interceptor): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(logger)
      .addInterceptor(req)
      .addInterceptor(res)
      .cache(cache)
      .build()
  }

  private fun retrofit(path: String, gsonConverter: GsonConverterFactory, rxConverter: RxJava2CallAdapterFactory, client: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(path)
      .addCallAdapterFactory(rxConverter)
      .addConverterFactory(gsonConverter)
      .client(client)
      .build()
  }

}



