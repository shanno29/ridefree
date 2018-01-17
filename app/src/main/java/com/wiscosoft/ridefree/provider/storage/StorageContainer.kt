package com.wiscosoft.ridefree.provider.storage

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.wiscosoft.ridefree.domain.payment.Payment
import com.wiscosoft.ridefree.domain.payment.PaymentStorage
import com.wiscosoft.ridefree.domain.place.Place
import com.wiscosoft.ridefree.domain.place.PlaceStorage
import com.wiscosoft.ridefree.domain.ride.Ride
import com.wiscosoft.ridefree.domain.ride.RideStorage
import com.wiscosoft.ridefree.domain.status.StatusConverters
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.domain.user.UserStorage

@Database(version = 41, entities = [Payment::class, Place::class, Ride::class, User::class], exportSchema = false)
@TypeConverters(Converters::class, StatusConverters::class)
abstract class StorageContainer : RoomDatabase() {
  abstract val paymentStorage: PaymentStorage
  abstract val placeStorage: PlaceStorage
  abstract val rideStorage: RideStorage
  abstract val userStorage: UserStorage
}