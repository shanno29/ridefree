package com.wiscosoft.ridefree.domain.user

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

// NETWORK
interface UserNetwork {

  @POST("ACCOUNT/CHANGE")
  fun modify(@Body user: User): Observable<User>

  @POST("ACCOUNT/REGISTER")
  fun register(@Body user: User): Observable<User>

  @POST("ACCOUNT/LOGON")
  fun logon(@Body user: User): Observable<User>

  @POST("ACCOUNT/LOGOFF")
  fun logoff(@Body user: User): Observable<User>
}