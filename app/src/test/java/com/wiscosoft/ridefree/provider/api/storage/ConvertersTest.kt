package com.wiscosoft.ridefree.provider.api.storage

import org.junit.Assert.*
import org.junit.Test
import java.util.Date

class ConvertersTest {

  @Test
  fun testDateTimeStampConverter() {
    val converters = Converters()
    val defaultTimeStamp: Long = 0
    val defaultDate = Date(0)

    assertEquals(defaultDate, converters.dateFromTimeStamp(defaultTimeStamp))
    assertEquals(defaultTimeStamp, converters.timestampFromDate(defaultDate))
  }

  @Test
  fun testIntListStringConverter() {
    val converters = Converters()
    val emptyList = emptyList<Int>()
    val emptyString = ""

    assertEquals(emptyString,converters.stringFromIntList(emptyList))
    assertEquals(emptyList, converters.intListFromString(emptyString))

    val list = listOf(1)
    val string = "1,"

    assertEquals(string, converters.stringFromIntList(list))
    assertEquals(list, converters.intListFromString(string))
  }

  @Test
  fun testStringListStringConverter() {
    val converters = Converters()
    val emptyList = emptyList<String>()
    val emptyString = ""

    assertEquals(emptyString, converters.stringFromStringList(emptyList))
    assertEquals(emptyList, converters.stringListFromString(emptyString))

    val list = listOf("A")
    val string = "A,"

    assertEquals(string, converters.stringFromStringList(list))
    assertEquals(list, converters.stringListFromString(string))
  }

}