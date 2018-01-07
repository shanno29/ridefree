package com.wiscosoft.ridefree.provider.network

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.google.gson.Gson
import com.wiscosoft.ridefree.core.Constants
import com.wiscosoft.ridefree.provider.prefs.Prefs
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory

val networkModule = Kodein.Module {

  bind<Cache>() with singleton {
    val file: File = instance()
    val cacheSize: Long = 10 * 1024 * 1024
    Cache(file, cacheSize)
  }

  bind<Gson>() with singleton {
    Gson()
  }

  bind<GsonConverterFactory>() with singleton {
    val gson: Gson = instance()
    GsonConverterFactory
      .create(gson)
  }

  bind<RxJava2CallAdapterFactory>() with singleton {
    RxJava2CallAdapterFactory
      .create()
  }

  bind<Interceptor>("Log") with singleton {
    HttpLoggingInterceptor()
      .setLevel(BODY)
  }

  bind<Interceptor>("Req") with singleton {
    val prefs: Prefs = instance()
    Interceptor {
      it.proceed(it.request().newBuilder().apply {
        prefs.cookies.get().forEach { addHeader("Cookie", it) }
      }.build())
    }
  }

  bind<Interceptor>("Res") with singleton {
    val prefs: Prefs = instance()
    Interceptor {
      it.proceed(it.request().apply {
        prefs.cookies.set(HashSet(headers("Set-Cookie")))
      })
    }
  }

  bind<SSLSocketFactory>() with singleton {
    SSLContext
      .getInstance(Constants.sslVersion)
      .apply { init(emptyArray(), emptyArray(), null) }
      .socketFactory
  }

  bind<OkHttpClient>() with singleton {
    val sslSocket: SSLSocketFactory = instance()
    val logger: Interceptor = instance("Log")
    val req: Interceptor = instance("Req")
    val res: Interceptor = instance("Res")
    val cache: Cache = instance()
    OkHttpClient.Builder()
      //.sslSocketFactory(sslSocket)
      .addInterceptor(logger)
      .addInterceptor(req)
      .addInterceptor(res)
      .cache(cache)
      .build()
  }

  bind<Retrofit>() with singleton {
    val rxCallAdapter: RxJava2CallAdapterFactory = instance()
    val gsonConverter: GsonConverterFactory = instance()
    val okHttpClient: OkHttpClient = instance()
    Retrofit.Builder().baseUrl(Constants.Api)
      .addCallAdapterFactory(rxCallAdapter)
      .addConverterFactory(gsonConverter)
      .client(okHttpClient)
      .build()

  }

}


