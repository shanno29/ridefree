package com.wiscosoft.ridefree.provider.storage

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.wiscosoft.ridefree.core.Constants

val storageModule = Kodein.Module {

  bind<RoomDatabase>() with singleton {
    val path: String = Constants.Database
    val context: Context = instance()
    Room.databaseBuilder(context, StorageContainer::class.java, path)
      .fallbackToDestructiveMigration()
      .allowMainThreadQueries()
      .build()
  }

  bind<StorageContainer>() with singleton {
    val roomDatabase: RoomDatabase = instance()
    roomDatabase as StorageContainer
  }
}
