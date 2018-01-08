package com.wiscosoft.ridefree.provider.api.storage

import android.arch.persistence.room.TypeConverter
import java.util.Date

class Converters {

  @TypeConverter
  fun timestampFromDate(date: Date): Long = date.time

  @TypeConverter
  fun dateFromTimeStamp(timestamp: Long): Date = Date(timestamp)

  @TypeConverter
  fun stringFromIntList(list: List<Int>): String = StringBuilder().apply { list.forEach { append("$it,") } }.toString()

  @TypeConverter
  fun intListFromString(value: String): List<Int> = value.split(",").filter(String::isNotEmpty).map(Integer::valueOf)

  @TypeConverter
  fun stringListFromString(value: String): List<String> = value.split(",").filter(String::isNotEmpty)

  @TypeConverter
  fun stringFromStringList(list: List<String>): String = StringBuilder().apply { list.forEach { append("$it,") } }.toString()

}