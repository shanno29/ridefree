package com.wiscosoft.ridefree.provider.storage

import android.arch.persistence.room.TypeConverter
import java.util.Date

class Converters {

  @TypeConverter
  fun toDate(timestamp: Long): Date = Date(timestamp)

  @TypeConverter
  fun toTimestamp(date: Date): Long = date.time

  @TypeConverter
  fun intArrayFromString(value: String): List<Int>? = value.split(",").filter { it.isNotEmpty() }.map { Integer.valueOf(it) }

  @TypeConverter
  fun stringFromIntArray(array: List<Int>?): String {
    val res = StringBuilder()
    array?.forEach { res.append("$it,") }
    return res.toString()
  }

  @TypeConverter
  fun stringArrayFromString(value: String?): List<String>? = value?.split(",")

  @TypeConverter
  fun stringFromStringArray(array: List<String>?): String {
    val res = StringBuilder()
    array?.forEach { res.append("$it,") }
    return res.toString()
  }
}