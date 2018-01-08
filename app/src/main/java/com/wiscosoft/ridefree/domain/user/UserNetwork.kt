package com.wiscosoft.ridefree.domain.user

import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.POST

// NETWORK
interface UserNetwork {

  @POST("ACCOUNT/CHANGE")
  fun modify(@Body user: User): Flowable<User>

  @POST("ACCOUNT/REGISTER")
  fun register(@Body user: User): Flowable<User>

  @POST("ACCOUNT/LOGON")
  fun logon(@Body user: User): Flowable<User>

  @POST("ACCOUNT/LOGOFF")
  fun logoff(@Body user: User): Flowable<User>
}