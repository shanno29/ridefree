package com.wiscosoft.ridefree.provider.storage

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton

class StorageModule {

  val bind = Kodein.Module {
    bind<RoomDatabase>() with singleton { roomDatabase(instance("db"), instance()) }
    bind<StorageContainer>() with singleton { storageContainer(instance()) }
  }

  private fun roomDatabase(path: String, context: Context): RoomDatabase {
    return Room.databaseBuilder(context, StorageContainer::class.java, path)
      .fallbackToDestructiveMigration()
      .allowMainThreadQueries()
      .build()
  }

  private fun storageContainer(roomDatabase: RoomDatabase): StorageContainer {
    return roomDatabase as StorageContainer
  }

}