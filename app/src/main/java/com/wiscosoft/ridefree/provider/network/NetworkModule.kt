package com.wiscosoft.ridefree.provider.network

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.wiscosoft.ridefree.core.app.Constants
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.UUID
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

  @Provides
  @Singleton
  fun gson(): Gson {
    return Gson()
  }

  @Provides
  @Singleton
  fun file(context: Context): File {
    return File(context.cacheDir, UUID.randomUUID().toString())
  }

  @Provides
  @Singleton
  fun cache(file: File): Cache {
    return Cache(file, Constants.cacheSize)
  }

  @Provides
  @Singleton
  fun prefs(context: Context): SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(context)
  }

  @Provides
  @Singleton
  @Named("Log")
  fun logInterceptor(): Interceptor {
    return HttpLoggingInterceptor()
      .setLevel(Level.BODY)
  }

  @Provides
  @Singleton
  @Named("Req")
  fun reqInterceptor(prefs: SharedPreferences): Interceptor {
    return Interceptor {
      it.proceed(it.request().newBuilder().apply {
        prefs.getStringSet("Cookies", emptySet()).forEach { addHeader("Cookie", it) }
      }.build()
    )}
  }

  @Provides
  @Singleton
  @Named("Res")
  fun resInterceptor(prefs: SharedPreferences): Interceptor {
    return Interceptor {
      it.proceed(it.request().apply {
        prefs.edit().putStringSet("Cookies", headers("Set-Cookie").toSet()).apply()
      }
    )}
  }

  @Provides
  @Singleton
  fun okHttp(@Named("Log") log: Interceptor, @Named("Req") req: Interceptor, @Named("Res") res: Interceptor, cache: Cache): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(log)
      .addInterceptor(req)
      .addInterceptor(res)
      .cache(cache)
      .build()
  }

  @Provides
  @Singleton
  fun retrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit.Builder()
      .baseUrl(Constants.apiUrl)
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create(gson))
      .client(okHttpClient)
      .build()
  }

}

//  @Provides
//  @Singleton
//  fun sslSocket(): SSLSocketFactory {
//    return SSLContext
//      .getInstance(Constants.sslVersion)
//      .apply { init(emptyArray(), emptyArray(), null) }
//      .socketFactory
//  }
