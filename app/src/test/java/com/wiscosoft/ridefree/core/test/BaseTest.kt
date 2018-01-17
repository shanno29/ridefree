package com.wiscosoft.ridefree.core.test

abstract class BaseTest {

  fun objectHelper(item: Any): Boolean {
    val equalInstances = item.equals(item)
    val equalToStrings = item.toString().equals(item.toString())
    val equalHashCodes = item.hashCode().equals(item.hashCode())
    return equalInstances && equalToStrings && equalHashCodes
  }

  fun cloneHelper(item: Any, default: Any): Boolean {
    val equalInstances = item.equals(default)
    val equalToStrings = item.toString().equals(default.toString())
    val equalHashCodes = item.hashCode().equals(default.hashCode())
    return !equalInstances && !equalToStrings && !equalHashCodes
  }

}