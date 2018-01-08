package com.wiscosoft.ridefree.provider.api.network

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
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory

val networkModule = Kodein.Module {

  bind<Cache>() with singleton {
    val size: Long = 10 * 1024 * 1024
    val file: File = instance()
    Cache(file, size)
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
    val level = HttpLoggingInterceptor.Level.BODY

    HttpLoggingInterceptor()
      .setLevel(level)
  }

  bind<Interceptor>("Req") with singleton {
    val prefs: Prefs = instance()
    val key = "Cookie"

    Interceptor {
      it.proceed(it.request().newBuilder().apply {
        prefs.cookies.get().forEach { addHeader(key, it) }
      }.build())
    }
  }

  bind<Interceptor>("Res") with singleton {
    val prefs: Prefs = instance()
    val key = "Set-Cookie"

    Interceptor {
      it.proceed(it.request().apply {
        prefs.cookies.set(HashSet(headers(key)))
      })
    }
  }

  bind<SSLSocketFactory>() with singleton {
    val version = Constants.sslVersion

    SSLContext
      .getInstance(version)
      .apply { init(emptyArray(), emptyArray(), null) }
      .socketFactory
  }

  bind<OkHttpClient>() with singleton {
    val factory: SSLSocketFactory = instance()
    val log: Interceptor = instance("Log")
    val req: Interceptor = instance("Req")
    val res: Interceptor = instance("Res")

    OkHttpClient.Builder()
      .sslSocketFactory(factory)
      .addInterceptor(log)
      .addInterceptor(req)
      .addInterceptor(res)
      .cache(instance())
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


