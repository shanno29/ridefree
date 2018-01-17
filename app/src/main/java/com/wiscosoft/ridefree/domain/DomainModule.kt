package com.wiscosoft.ridefree.domain

import com.wiscosoft.ridefree.domain.payment.PaymentModule
import com.wiscosoft.ridefree.domain.place.PlaceModule
import com.wiscosoft.ridefree.domain.ride.RideModule
import com.wiscosoft.ridefree.domain.user.UserModule

import dagger.Module

@Module(includes = [
  PaymentModule::class,
  PlaceModule::class,
  RideModule::class,
  UserModule::class
])
class DomainModule