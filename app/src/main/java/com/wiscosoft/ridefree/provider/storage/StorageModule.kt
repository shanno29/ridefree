package com.wiscosoft.ridefree.provider.storage

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.wiscosoft.ridefree.core.app.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

  @Provides
  @Singleton
  fun roomDb(context: Context): RoomDatabase {
    val type = StorageContainer::class.java
    val path = Constants.Database
    return Room.databaseBuilder(context, type, path)
      .fallbackToDestructiveMigration()
      .allowMainThreadQueries()
      .build()
  }

  @Provides
  @Singleton
  fun storageContainer(roomDatabase: RoomDatabase): StorageContainer {
    return roomDatabase as StorageContainer
  }

}