package com.wiscosoft.ridefree.provider.storage

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.wiscosoft.ridefree.domain.payment.Payment
import com.wiscosoft.ridefree.domain.payment.api.PaymentStorage
import com.wiscosoft.ridefree.domain.place.Place
import com.wiscosoft.ridefree.domain.place.api.PlaceStorage
import com.wiscosoft.ridefree.domain.ride.Ride
import com.wiscosoft.ridefree.domain.ride.api.RideStorage
import com.wiscosoft.ridefree.domain.ride.status.StatusConverter
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.domain.user.api.UserStorage

@Database(version = 36, entities = [Payment::class, Place::class, Ride::class, User::class], exportSchema = false)
@TypeConverters(Converters::class, StatusConverter::class)
abstract class StorageContainer : RoomDatabase() {

  abstract fun paymentStorage(): PaymentStorage

  abstract fun placeStorage(): PlaceStorage

  abstract fun rideStorage(): RideStorage

  abstract fun userStorage(): UserStorage
}